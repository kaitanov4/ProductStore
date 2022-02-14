package kz.kaitanov.setronica.service;

import kz.kaitanov.setronica.model.Product;
import kz.kaitanov.setronica.service.pagination.PaginationService;

import java.util.Map;

public interface ProductService extends PaginationService<Product> {

    void addProduct(Product product);

    void updateProduct(Product product);

    void removeProduct(Long id);

    Product getByIdAndParameters(Long id, Map<String, Object> parameters);

}