package com.wup.cc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String base;
    private int type;
    private Date date;
    @Column(name = "chf", columnDefinition = "Decimal(10,5)")
    private BigDecimal chf;
    @Column(name = "usd", columnDefinition = "Decimal(10,5)")
    private BigDecimal usd;
    @Column(name = "eur", columnDefinition = "Decimal(10,5)")
    private BigDecimal eur;
    @Column(name = "cad", columnDefinition = "Decimal(10,5)")
    private BigDecimal cad;
    @Column(name = "gbp", columnDefinition = "Decimal(10,5)")
    private BigDecimal gbp;

    public int getId() {
        return id;
    }

    public ExchangeRate() {
    }

    public String getBase() {
        return base;
    }

    public int getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public BigDecimal getChf() {
        return chf;
    }

    public BigDecimal getUsd() {
        return usd;
    }

    public BigDecimal getEur() {
        return eur;
    }

    public BigDecimal getCad() {
        return cad;
    }

    public BigDecimal getGbp() {
        return gbp;
    }


    @Override
    public String toString() {
        return "Rate{" +
                "base:" + base + '\n' +
                "type:" + type + '\n' +
                "date:" + date + '\n' +
                "chf:" + chf + '\n' +
                "usd:" + usd + '\n' +
                "eur:" + eur + '\n' +
                "cad:" + cad + '\n' +
                "gbp:" + gbp + '\n' +
                '}';
    }
}