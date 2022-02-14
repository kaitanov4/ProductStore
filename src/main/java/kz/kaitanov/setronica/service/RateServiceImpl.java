package kz.kaitanov.setronica.service;

import kz.kaitanov.setronica.dao.RateDao;
import kz.kaitanov.setronica.model.Rate;
import kz.kaitanov.setronica.model.enums.CurrencyEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RateServiceImpl implements RateService {

    private final RateDao rateDao;

    public RateServiceImpl(RateDao rateDao) {
        this.rateDao = rateDao;
    }

    @Transactional
    @Override
    public void addRate(Rate rate) {
        rateDao.addRate(rate);
    }

    @Override
    public Double getValueByCurrency(CurrencyEnum currency) {
        return rateDao.getValueByCurrency(currency);
    }

}