package kz.kaitanov.setronica.service;

import kz.kaitanov.setronica.config.exception.ProductNotFoundException;
import kz.kaitanov.setronica.dao.ProductDao;
import kz.kaitanov.setronica.model.Product;
import kz.kaitanov.setronica.model.ProductDetails;
import kz.kaitanov.setronica.model.dto.PageDto;
import kz.kaitanov.setronica.model.enums.CurrencyEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;
    private final RateService rateService;

    public ProductServiceImpl(ProductDao productDao, RateService rateService) {
        this.productDao = productDao;
        this.rateService = rateService;
    }

    @Transactional
    @Override
    public void addProduct(Product product) {
        for (ProductDetails productDetails : product.getProductDetailsList()) {
            productDetails.setProduct(product);
        }
        product.getPrice().setProduct(product);
        product.setCreatedDate(LocalDate.now());
        productDao.addProduct(product);
    }

    @Transactional
    @Override
    public void updateProduct(Product product) {
        product.setModifiedDate(LocalDate.now());
        productDao.updateProduct(product);
    }

    @Transactional
    @Override
    public void removeProduct(Long id) {
        productDao.removeProduct(id);
    }

    @Override
    public Product getByIdAndParameters(Long id, Map<String, Object> parameters) {
        Product product = productDao.getByIdAndParameters(id, parameters)
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                format("Product with id: %s and locale: %s, not found", id, parameters.get("locale"))
                        )
                );
        CurrencyEnum currency = (CurrencyEnum) parameters.get("currency");
        if (!currency.equals(CurrencyEnum.USD)) {
            product.getPrice().setAmount(product.getPrice().getAmount().multiply(new BigDecimal(rateService.getValueByCurrency(currency).toString())));
            product.getPrice().setCurrency(currency);
        }
        return product;
    }

    @Override
    public PageDto<Product> getPageDtoByParameters(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        List<Product> productList = productDao.getItemsByParameters(currentPage, itemsOnPage, parameters);
        CurrencyEnum currency = (CurrencyEnum) parameters.get("currency");
        if (!currency.equals(CurrencyEnum.USD)) {
            Double value = rateService.getValueByCurrency(currency);
            for (Product product : productList) {
                product.getPrice().setAmount(product.getPrice().getAmount().multiply(new BigDecimal(value.toString())));
                product.getPrice().setCurrency(currency);
            }
        }
        return new PageDto<>(productDao.getCountByParameters(parameters), productList);
    }

}