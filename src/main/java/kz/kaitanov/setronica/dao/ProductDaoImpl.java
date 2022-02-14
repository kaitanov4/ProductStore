package kz.kaitanov.setronica.dao;

import kz.kaitanov.setronica.model.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductDaoImpl implements ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addProduct(Product product) {
        entityManager.persist(product);
    }

    @Override
    public void updateProduct(Product product) {
        entityManager.merge(product);
    }

    @Override
    public void removeProduct(Long id) {
        entityManager.remove(entityManager.find(Product.class, id));
    }

    @Override
    public Optional<Product> getByIdAndParameters(Long id, Map<String, Object> parameters) {
        try {
            return Optional.ofNullable(
                    entityManager.createQuery("SELECT p FROM Product p JOIN FETCH p.productDetailsList pd WHERE p.id = :id AND pd.locale = :locale", Product.class)
                            .setParameter("id", id)
                            .setParameter("locale", parameters.get("locale"))
                            .getSingleResult()
            );
        } catch (NoResultException ignore) {
        }
        return Optional.empty();
    }

    @Override
    public List<Product> getItemsByParameters(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        return entityManager.createQuery("SELECT p FROM Product p JOIN FETCH p.productDetailsList pd WHERE pd.name LIKE CONCAT('%', :name, '%') AND pd.description LIKE CONCAT('%', :description, '%') AND pd.locale = :locale", Product.class)
                .setParameter("name", parameters.get("name"))
                .setParameter("description", parameters.get("description"))
                .setParameter("locale", parameters.get("locale"))
                .setFirstResult((currentPage - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage)
                .getResultList();
    }

    @Override
    public Long getCountByParameters(Map<String, Object> parameters) {
        return entityManager.createQuery("SELECT COUNT(p.id) FROM Product p JOIN p.productDetailsList pd WHERE pd.name LIKE CONCAT('%', :name, '%') AND pd.description LIKE CONCAT('%', :description, '%') AND pd.locale = :locale", Long.class)
                .setParameter("name", parameters.get("name"))
                .setParameter("description", parameters.get("description"))
                .setParameter("locale", parameters.get("locale"))
                .getSingleResult();
    }

}