package kz.kaitanov.setronica.dao;

import kz.kaitanov.setronica.model.Rate;
import kz.kaitanov.setronica.model.enums.CurrencyEnum;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RateDaoImpl implements RateDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRate(Rate rate) {
        entityManager.persist(rate);
    }

    @Override
    public Double getValueByCurrency(CurrencyEnum currency) {
        return entityManager.createQuery("SELECT r.value FROM Rate r WHERE r.currency = :currency", Double.class)
                .setParameter("currency", currency)
                .getSingleResult();
    }

}