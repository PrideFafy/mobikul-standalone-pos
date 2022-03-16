package com.webkul.mobikul.mobikulstandalonepos.db.dao;

import com.j256.ormlite.dao.Dao;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.OrderEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by aman.gupta on 25/1/18. @Webkul Software Private limited
 */

public interface OrderDao extends Dao<OrderEntity,Integer> {
    //@Query("SELECT * FROM OrderEntity ORDER BY orderId DESC")
    List<OrderEntity> getAll() throws SQLException;

    //@Query("SELECT * FROM OrderEntity WHERE orderId IN (:OrderId)")
    OrderEntity loadByIds(int OrderId) throws SQLException;

    //@Query("SELECT * FROM OrderEntity WHERE orderId LIKE (:searchText)")
    List<OrderEntity> getSearchOrders(String searchText) throws SQLException;

    //@Query("UPDATE OrderEntity SET refunded_order_id = :currentOrderId WHERE orderId = :returnedOrderId")
    void updateRefundedOrderId(String currentOrderId, int returnedOrderId) throws SQLException;

    //@Insert
    long[] insertAll(OrderEntity... orderEntities) throws SQLException;

//    @Delete
//    void delete(OrderEntity OrderEntity);

    //@Query("DELETE FROM OrderEntity")
    void delete();

}
