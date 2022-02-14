package kz.kaitanov.setronica.dao;

import kz.kaitanov.setronica.model.Rate;
import kz.kaitanov.setronica.model.enums.CurrencyEnum;

public interface RateDao {

    void addRate(Rate rate);

    Double getValueByCurrency(CurrencyEnum currency);

}
