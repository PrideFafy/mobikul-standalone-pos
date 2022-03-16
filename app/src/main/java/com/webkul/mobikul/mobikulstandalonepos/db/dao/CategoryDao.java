package com.webkul.mobikul.mobikulstandalonepos.db.dao;


import com.j256.ormlite.dao.Dao;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by aman.gupta on 8/1/18.
 */

public interface CategoryDao extends Dao<Category, Integer> {
   // @Query("SELECT * FROM Category")
    List<Category> getAll() throws SQLException;

    //@Query("SELECT * FROM Category WHERE cId IN (:CategoryIds)")
    List<Category> loadAllByIds(int[] CategoryIds) throws SQLException;

    //@Query("SELECT * FROM Category WHERE is_include_in_drawer_menu = :isIncludeInDrawerMenu AND is_active = :isActive")
    List<Category> getCategoryIncludedInDrawerMenu(boolean isIncludeInDrawerMenu, boolean isActive) throws SQLException;

    //@Query("UPDATE Category SET category_name = :categoryName, is_active = :isActive, is_include_in_drawer_menu = :isIncludeInDrawerMenu WHERE cId = :cId")
    void updateCategoryById(String categoryName, boolean isActive, boolean isIncludeInDrawerMenu, int cId) throws SQLException;

    //@Insert
    void insertAll(Category... Categorys) throws SQLException;



   // @Query("DELETE FROM Category")
    void delete();


}
