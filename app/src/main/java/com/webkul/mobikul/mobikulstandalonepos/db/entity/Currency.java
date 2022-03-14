package com.webkul.mobikul.mobikulstandalonepos.db.entity;


import android.databinding.BaseObservable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by aman.gupta on 14/3/18. @Webkul Software Private limited
 */
@Entity(name = "Currency")
public class Currency extends BaseObservable {
    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "flag")
    private int flag = -1;
    @Column(name = "rate")
    private float rate;
    @Column(name = "formated_rate")
    private String formatedRate;
    @Column(name = "selected")
    private boolean isSelected;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getFormatedRate() {
        return formatedRate;
    }

    public void setFormatedRate(String formatedRate) {
        this.formatedRate = formatedRate;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}