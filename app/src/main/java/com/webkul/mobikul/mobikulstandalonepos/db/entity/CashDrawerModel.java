package com.webkul.mobikul.mobikulstandalonepos.db.entity;


import com.j256.ormlite.table.DatabaseTable;
import com.webkul.mobikul.mobikulstandalonepos.db.dao.CashDrawerDaoImpl;
import com.webkul.mobikul.mobikulstandalonepos.model.CashDrawerItems;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by aman.gupta on 15/2/18. @Webkul Software Private limited
 */
@Entity
@DatabaseTable( daoClass = CashDrawerDaoImpl.class)
public class CashDrawerModel implements Serializable {

    @Id
    @Column(name = "date")
    @NotNull
    private String date;

    //@TypeConverters(DataConverter.class)
    @Column(name = "cash_drawer_items")
    private List<CashDrawerItems> cashDrawerItems;

    @Column(name = "opening_balance")
    private String openingBalance;

    @Column(name = "formatted_opening_balance")
    private String formattedOpeningBalance;

    @Column(name = "closing_balance")
    private String closingBalance;

    @Column(name = "formatted_closing_balance")
    private String formattedClosingBalance;

    @Column(name = "net_revenue")
    private String netRevenue;

    @Column(name = "formatted_net_revenue")
    private String formattedNetRevenue;

    @Column(name = "in_amount")
    private String inAmount;

    @Column(name = "formatted_in_amount")
    private String formattedInAmount;

    @Column(name = "out_amount")
    private String outAmount;

    @Column(name = "formatted_out_amount")
    private String formattedOutAmount;

    @Column(name = "is_synced")
    private String isSynced;

    public String getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(String openingBalance) {
        this.openingBalance = openingBalance;
    }

    public String getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(String closingBalance) {
        this.closingBalance = closingBalance;
    }

    public String getNetRevenue() {
        return netRevenue;
    }

    public void setNetRevenue(String netRevenue) {
        this.netRevenue = netRevenue;
    }

    public String getInAmount() {
        if (inAmount == null)
            return "0.00";
        return inAmount;
    }

    public void setInAmount(String inAmount) {
        this.inAmount = inAmount;
    }

    public String getOutAmount() {
        if (outAmount == null)
            return "0.00";
        return outAmount;
    }

    public void setOutAmount(String outAmount) {
        this.outAmount = outAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIsSynced() {
        return isSynced;
    }

    public void setIsSynced(String isSynced) {
        this.isSynced = isSynced;
    }

    public List<CashDrawerItems> getCashDrawerItems() {
        if (cashDrawerItems == null)
            cashDrawerItems = new ArrayList<>();
        return cashDrawerItems;
    }

    public void setCashDrawerItems(List<CashDrawerItems> cashDrawerItems) {
        this.cashDrawerItems = cashDrawerItems;
    }

    public String getFormattedOpeningBalance() {
        return formattedOpeningBalance;
    }

    public void setFormattedOpeningBalance(String formattedOpeningBalance) {
        this.formattedOpeningBalance = formattedOpeningBalance;
    }

    public String getFormattedClosingBalance() {
        return formattedClosingBalance;
    }

    public void setFormattedClosingBalance(String formattedClosingBalance) {
        this.formattedClosingBalance = formattedClosingBalance;
    }

    public String getFormattedNetRevenue() {
        return formattedNetRevenue;
    }

    public void setFormattedNetRevenue(String formattedNetRevenue) {
        this.formattedNetRevenue = formattedNetRevenue;
    }

    public String getFormattedInAmount() {
        return formattedInAmount;
    }

    public void setFormattedInAmount(String formattedInAmount) {
        this.formattedInAmount = formattedInAmount;
    }

    public String getFormattedOutAmount() {
        return formattedOutAmount;
    }

    public void setFormattedOutAmount(String formattedOutAmount) {
        this.formattedOutAmount = formattedOutAmount;
    }
}