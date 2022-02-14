package kz.kaitanov.setronica.service;

import kz.kaitanov.setronica.model.Rate;
import kz.kaitanov.setronica.model.enums.CurrencyEnum;

public interface RateService {

    void addRate(Rate rate);

    Double getValueByCurrency(CurrencyEnum currency);

}