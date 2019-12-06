package com.wup.cc.service;

import com.wup.cc.model.ExchangeRate;
import com.wup.cc.constants.ExchangeRateConstant;
import com.wup.cc.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@Service
public class ExchangeRateService {

    @Autowired
    ExchangeRateRepository exchangeRateRepository;

    public ExchangeRate getExchangeRateRecord(String base, int type) {
        return exchangeRateRepository.findByBaseAndType(base, type);
    }

    public double convertFromBaseToTarget(String base, int type, String target, BigDecimal amount) {
        ExchangeRate rateRecord = getExchangeRateRecord(base, type);
        HashMap<String, BigDecimal> ExchangeRateMap = getExchangeRateMap(rateRecord);

        return ExchangeRateMap.get(target).multiply(amount).doubleValue();
    }

    private HashMap<String, BigDecimal> getExchangeRateMap(ExchangeRate rateRecord) {
        HashMap<String, BigDecimal> getExchangeRateMap = new HashMap<>();
        getExchangeRateMap.put("chf", rateRecord.getChf());
        getExchangeRateMap.put("usd", rateRecord.getUsd());
        getExchangeRateMap.put("eur", rateRecord.getEur());
        getExchangeRateMap.put("cad", rateRecord.getCad());
        getExchangeRateMap.put("gbp", rateRecord.getGbp());
        return getExchangeRateMap;
    }

    public String getFormattedResponse(Map<String, String> body) {
        BigDecimal amount = new BigDecimal(body.get("amount"));
        int type = Integer.parseInt(body.get("type"));
        String base = body.get("base");
        String target = body.get("to");
        double conversionResult = convertFromBaseToTarget(base, type, target, amount);
        String typeString = getTypeString(type);

        return " Result of conversion: " + amount
                + ' '
                + base
                + " is "
                + conversionResult
                + " in "
                + target
                + " on " + typeString;
    }

    private String getTypeString(int type){
        return ExchangeRateConstant.BUYING_RATE.getValue() == type ?
                ExchangeRateConstant.BUYING_RATE.toString() :
                ExchangeRateConstant.EXCHANGE_RATE.toString();

    }

    public float cheatConvert( Map<String, String> body){
        int amount = Integer.parseInt(body.get("amount"));
        String base = body.get("base");
        String target = body.get("target");

        MonetaryAmount unit = Monetary.getDefaultAmountFactory().setCurrency(base)
                .setNumber(1).create();

        CurrencyConversion conversion = MonetaryConversions.getConversion(target);
        MonetaryAmount convertedAmount = unit.with(conversion);

        return (amount * convertedAmount.getNumber().floatValue());
    }
}
