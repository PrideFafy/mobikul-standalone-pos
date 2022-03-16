package com.webkul.mobikul.mobikulstandalonepos.db.dao;

import com.j256.ormlite.dao.Dao;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by aman.gupta on 9/1/18.
 */

public interface ProductDao extends Dao<Product,Integer> {
    //@Query("SELECT * FROM Product")
    List<Product> getAll() throws SQLException;

   // @Query("SELECT * FROM Product WHERE pId IN (:ProductIds)")
    List<Product> loadProductsByIds(int ProductIds) throws SQLException;

   // @Query("SELECT * FROM Product WHERE product_name LIKE (:searchText)")
    List<Product> getSearchData(String searchText) throws SQLException;

    //@Query("SELECT * FROM Product WHERE is_enabled = :isEnabled")
    List<Product> getEnabledProduct(boolean isEnabled) throws SQLException;

    //@Query("SELECT * FROM Product WHERE sku IN (:sku)")
    Product checkSkuExist(String sku) throws SQLException;

   // @Query("SELECT * FROM Product WHERE CAST(quantity as decimal)<= :quantity")
    List<Product> getLowStockProducts(int quantity) throws SQLException;

    //@Query("SELECT * FROM Product WHERE barCode = :barcode")
    Product getProductByBarcode(String barcode) throws SQLException;

    //@Query("UPDATE Product SET quantity = :qty WHERE pId = :pId")
    void updateProductQty(String qty, int pId) throws SQLException;

    //@Query("UPDATE Product SET image = :path WHERE pId = :pId")
    void updateProductImages(String path, long pId) throws SQLException;

//    @Query("UPDATE Product SET image = :imagePath, is_enabled = :isEnabled, product_name = :ProductName, sku = :sku, price = :price" +
//            ", special_price = :specialPrice, is_taxable_goods_applied = :isTaxableGoodsApplied, track_inventory= :trackInventory" +
//            ", quantity = :qty , stock_availability = :inStock, weight = :weight, productCategories = :productCategories" +
//            ", options = :productOptions, product_tax = :productTax WHERE pId = :pId")

   // @TypeConverters(DataConverter.class)
    void updateProduct(String imagePath
            , boolean isEnabled
            , String ProductName
            , String sku
            , String price
            , String specialPrice
            , boolean isTaxableGoodsApplied
            , boolean trackInventory
            , String qty
            , boolean inStock
            , String weight
            , String productCategories
            , String productOptions
            , String productTax
            , int pId) throws SQLException;


    //@Insert
    long[] insertAll(Product... Products) throws SQLException;



    //@Query("DELETE FROM Product")
    void delete();
}

