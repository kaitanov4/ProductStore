package kz.kaitanov.setronica.controller;

import kz.kaitanov.setronica.model.Product;
import kz.kaitanov.setronica.model.dto.PageDto;
import kz.kaitanov.setronica.model.dto.ResponseDto;
import kz.kaitanov.setronica.model.enums.CurrencyEnum;
import kz.kaitanov.setronica.model.enums.LocaleEnum;
import kz.kaitanov.setronica.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/user/product")
public class UserProductRestController {

    private final ProductService productService;

    public UserProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ResponseDto<Product> getById(@PathVariable("productId") Long productId,
                                        @RequestParam(value = "locale") String locale,
                                        @RequestParam(value = "currency") String currency) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("locale", LocaleEnum.valueOf(locale));
        parameters.put("currency", CurrencyEnum.valueOf(currency));
        return ResponseDto.ok(productService.getByIdAndParameters(productId, parameters));
    }

    @GetMapping
    public ResponseDto<PageDto<Product>> getByParameters(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                                         @RequestParam(value = "itemsOnPage", defaultValue = "10") Integer itemsOnPage,
                                                         @RequestParam(value = "name", defaultValue = "") String name,
                                                         @RequestParam(value = "description", defaultValue = "") String description,
                                                         @RequestParam(value = "locale") String locale,
                                                         @RequestParam(value = "currency") String currency) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("description", description);
        parameters.put("locale", LocaleEnum.valueOf(locale));
        parameters.put("currency", CurrencyEnum.valueOf(currency));
        return ResponseDto.ok(productService.getPageDtoByParameters(currentPage, itemsOnPage, parameters));
    }

}