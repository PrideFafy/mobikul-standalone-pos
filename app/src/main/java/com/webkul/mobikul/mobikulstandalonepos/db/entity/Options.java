package com.webkul.mobikul.mobikulstandalonepos.db.entity;


import android.arch.persistence.room.Ignore;

import android.arch.persistence.room.TypeConverters;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.webkul.mobikul.mobikulstandalonepos.BR;
import com.webkul.mobikul.mobikulstandalonepos.db.converters.DataConverter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by aman.gupta on 15/2/18. @Webkul Software Private limited
 */
@Entity
public class Options extends BaseObservable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "nadministrator_id_seq")
    @Column(name = "option_id")
    private int optionId;

    @Column(name = "option_name")
    private String optionName;

    @Column(name = "option_type")
    private String type;

    @Column(name = "option_values")
    private List<OptionValues> optionValues;

    @Column(name = "sort_order")
    private int sortOrder;
    @Ignore
    private boolean selected;
    @Ignore
    private boolean displayError;

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    @Bindable
    public String getOptionName() {
        if (optionName == null)
            return "";
        return optionName;
    }

    @Bindable({"displayError", "optionName"})
    public String getOptionNameError() {
        if (!isDisplayError()) {
            return "";
        }
        if (getOptionName().isEmpty()) {
            return "OPTION NAME IS EMPTY!";
        }
        return "";
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
        notifyPropertyChanged(BR.optionName);
    }

    @Bindable
    public String getType() {
        if (type == null)
            return type;
        return type;
    }

    public void setType(String type) {
        this.type = type;
        notifyPropertyChanged(BR.type);
    }

    @Bindable
    public List<OptionValues> getOptionValues() {
        if (optionValues == null) {
            optionValues = new ArrayList<>();
            return optionValues;
        }
        return optionValues;
    }

    public void setOptionValues(List<OptionValues> optionValues) {
        this.optionValues = optionValues;
        notifyPropertyChanged(BR.optionValues);
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Bindable
    public boolean isDisplayError() {
        return displayError;
    }

    public void setDisplayError(boolean displayError) {
        this.displayError = displayError;
        notifyPropertyChanged(BR.displayError);
    }

    @Bindable
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        notifyPropertyChanged(BR.selected);
    }
}
