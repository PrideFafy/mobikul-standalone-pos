package com.webkul.mobikul.mobikulstandalonepos.db.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.Product;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ProductDaoImpl extends BaseDaoImpl<Product,Integer> implements  ProductDao {
    protected ProductDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Product.class);
    }

    @Override
    public List<Product> getAll() throws SQLException {
        return queryForAll();
    }

    @Override
    public List<Product> loadProductsByIds(int ProductIds) throws SQLException {
        return queryBuilder().where().in("pId", ProductIds).query();
    }

    @Override
    public List<Product> getSearchData(String searchText) throws SQLException {
        return queryBuilder().where().like("pId",searchText).query();
    }

    @Override
    public List<Product> getEnabledProduct(boolean isEnabled) throws SQLException {
        return queryForEq("is_enabled", isEnabled);
    }

    @Override
    public Product checkSkuExist(String sku) throws SQLException {
        return queryBuilder().where().eq(sku,sku).queryForFirst();
    }

    @Override
    public List<Product> getLowStockProducts(int quantity) throws SQLException {
        return queryForEq("quantity", quantity);
    }

    @Override
    public Product getProductByBarcode(String barcode) throws SQLException {
        return queryBuilder().where().eq("barCode", barcode).queryForFirst();
    }

    @Override
    public void updateProductQty(String qty, int pId) throws SQLException {
        Product product = queryForId(pId);
        product.setQuantity(qty);
        update(product);
    }

    @Override
    public void updateProductImages(String path, long pId) throws SQLException {
        Product product = queryForId((int) pId);
        product.setImage(path);
        update(product);
    }

    @Override
    public void updateProduct(String imagePath, boolean isEnabled, String ProductName, String sku,
                              String price, String specialPrice, boolean isTaxableGoodsApplied, boolean trackInventory, String qty,
                              boolean inStock, String weight, String productCategories, String productOptions, String productTax, int pId) throws SQLException {
        Product product = queryForId(pId);
        product.setImage(imagePath);
        product.setEnabled(isEnabled);
        product.setProductName(ProductName);
        product.setSku(sku);
        product.setPrice(price);
        product.setSpecialPrice(specialPrice);
        product.setTaxableGoodsApplied(isTaxableGoodsApplied);
        product.setTrackInventory(trackInventory);
        product.setStock(inStock);
        product.setWeight(weight);
       // product.set
        update(product);
    }

    @Override
    public long[] insertAll(Product... Products) throws SQLException {
        return new long[create(Arrays.asList(Products))];
    }

    @Override
    public void delete() {

    }
}
