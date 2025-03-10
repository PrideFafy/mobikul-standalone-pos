package com.webkul.mobikul.mobikulstandalonepos.db.entity;

import android.arch.persistence.room.Ignore;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.j256.ormlite.table.DatabaseTable;
import com.webkul.mobikul.mobikulstandalonepos.db.dao.TaxDaoImpl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by aman.gupta on 26/2/18. @Webkul Software Private limited
 */
@Entity
@DatabaseTable(daoClass = TaxDaoImpl.class)
public class Tax extends BaseObservable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "nadministrator_id_seq")
    private int taxId;

    @Column(name = "tax_name")
    private String taxName;

    @Column(name = "is_enabled")
    private boolean enabled;

    @Column(name = "type")
    private String type;

    @Column(name = "tax_rate")
    private String taxRate;

    @Ignore
    private boolean displayError;

    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    @Bindable
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
       // notifyPropertyChanged(BR.enabled);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Bindable
    public String getTaxName() {
        if (taxName == null)
            return "";
        return taxName;
    }

    @Bindable({"displayError", "taxName"})
    public String getTaxNameError() {
        if (!isDisplayError()) {
            return "";
        }
        if (getTaxName().isEmpty()) {
            return "TAX NAME IS EMPTY!";
        }
        return "";
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
        //notifyPropertyChanged(BR.taxName);
    }

    @Bindable
    public String getTaxRate() {
        if (taxRate == null)
            return "";
        return taxRate;
    }

    @Bindable({"displayError", "taxRate"})
    public String getTaxRateError() {
        if (!isDisplayError()) {
            return "";
        }
        if (getTaxRate().isEmpty()) {
            return "TAX RATE IS EMPTY!";
        }
        return "";
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
        //notifyPropertyChanged(BR.taxRate);
    }

    @Bindable
    public boolean isDisplayError() {
        return displayError;
    }

    public void setDisplayError(boolean displayError) {
        this.displayError = displayError;
        //notifyPropertyChanged(BR.displayError);
    }
}