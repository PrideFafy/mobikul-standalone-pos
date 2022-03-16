package com.webkul.mobikul.mobikulstandalonepos.db.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.Category;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class CategoryDaoImpl extends BaseDaoImpl<Category, Integer> implements CategoryDao{
    protected CategoryDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Category.class);
    }

    @Override
    public List<Category> getAll() throws SQLException {
        return queryForAll();
    }

    @Override
    public List<Category> loadAllByIds(int[] CategoryIds) throws SQLException {
        return queryBuilder().where().in("cId", CategoryIds).query();
    }

    @Override
    public List<Category> getCategoryIncludedInDrawerMenu(boolean isIncludeInDrawerMenu, boolean isActive) throws SQLException {
        return queryBuilder().where().eq("is_include_in_drawer_menu",isIncludeInDrawerMenu).and().eq("is_active",isActive).query();
    }

    @Override
    public void updateCategoryById(String categoryName, boolean isActive, boolean isIncludeInDrawerMenu, int cId) throws SQLException {
        Category category = queryForId(cId);
        category.setActive(isActive);
        category.setCategoryName(categoryName);
        category.setIncludeInDrawerMenu(isIncludeInDrawerMenu);
        update(category);
    }

    @Override
    public void insertAll(Category... Categorys) throws SQLException {
        create(Arrays.asList(Categorys));
    }

    @Override
    public void delete() {

    }
}
