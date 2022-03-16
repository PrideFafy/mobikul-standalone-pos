package com.webkul.mobikul.mobikulstandalonepos.db.entity;

import android.arch.persistence.room.Ignore;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.j256.ormlite.table.DatabaseTable;
import com.webkul.mobikul.mobikulstandalonepos.db.dao.CategoryDaoImpl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by aman.gupta on 6/1/18.
 */
@Entity(name = "Category")
@DatabaseTable(daoClass = CategoryDaoImpl.class)
public class Category extends BaseObservable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "nadministrator_id_seq")
    private int cId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "is_active")
    private boolean active;

    @Column(name = "is_include_in_drawer_menu")
    private boolean includeInDrawerMenu;

    @Column(name = "category_icon")
    private int icon;

    @Column(name = "level")
    private int level;

    @Column(name = "parent_id")
    private int parentId;

    @Column(name = "path")
    private String path;

    @Ignore
    private boolean displayError;

    @Ignore
    private boolean isSelected;

    public int getCId() {
        return cId;
    }

    public void setCId(int cId) {
        this.cId = cId;
    }

    public boolean isIncludeInDrawerMenu() {
        return includeInDrawerMenu;
    }

    public void setIncludeInDrawerMenu(boolean includeInDrawerMenu) {
        this.includeInDrawerMenu = includeInDrawerMenu;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Bindable
    public String getCategoryName() {
        if (categoryName == null)
            return "";
        return categoryName;
    }


    @Bindable({"displayError", "categoryName"})
    public String getCategoryNameError() {
        if (!isDisplayError()) {
            return "";
        }
        if (getCategoryName().isEmpty()) {
            return "CATEGORY NAME IS EMPTY!";
        }
        return "";
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
       // notifyPropertyChanged(BR.categoryName);
    }


    @Bindable
    public boolean isDisplayError() {
        return displayError;
    }

    public void setDisplayError(boolean displayError) {
        this.displayError = displayError;
       // notifyPropertyChanged(BR.displayError);
    }


    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
