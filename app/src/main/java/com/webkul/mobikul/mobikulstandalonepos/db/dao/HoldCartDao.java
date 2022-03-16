package com.webkul.mobikul.mobikulstandalonepos.db.dao;

import com.j256.ormlite.dao.Dao;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.HoldCart;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by aman.gupta on 5/2/18. @Webkul Software Private limited
 */

public interface HoldCartDao extends Dao<HoldCart,Integer> {
   // @Query("SELECT * FROM HoldCart ORDER BY holdCartId DESC")
    List<HoldCart> getAll() throws SQLException;

    //@Query("SELECT * FROM HoldCart WHERE holdCartId IN (:holdCartIds)")
    List<HoldCart> loadAllByIds(int[] holdCartIds) throws SQLException;

    //@Query("SELECT * FROM HoldCart WHERE holdCartId LIKE (:searchText)")
    List<HoldCart> getSearchHoldCart(String searchText) throws SQLException;

   // @Insert
    long[] insertAll(HoldCart... holdCarts) throws SQLException;

    //    @Delete
//    void delete(HoldCart HoldCart);
//
    //@Query("DELETE FROM HoldCart")
    void delete();

    //@Query("DELETE FROM HoldCart WHERE holdCartId IN (:holdCartId)")
    void delete(long holdCartId) throws SQLException;


}
