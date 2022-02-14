package kz.kaitanov.setronica.dao;

import kz.kaitanov.setronica.dao.pagination.PaginationDao;
import kz.kaitanov.setronica.model.Product;

import java.util.Map;
import java.util.Optional;

public interface ProductDao extends PaginationDao<Product> {

    void addProduct(Product product);

    void updateProduct(Product product);

    void removeProduct(Long id);

    Optional<Product> getByIdAndParameters(Long id, Map<String, Object> parameters);

}