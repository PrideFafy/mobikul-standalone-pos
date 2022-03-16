package com.webkul.mobikul.mobikulstandalonepos.db.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.OrderEntity;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class OrderDaoImpl extends BaseDaoImpl<OrderEntity,Integer> implements OrderDao {
    protected OrderDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, OrderEntity.class);
    }

    @Override
    public List<OrderEntity> getAll() throws SQLException {
        return queryForAll();
    }

    @Override
    public OrderEntity loadByIds(int OrderId) throws SQLException {
        return queryBuilder().where().eq("orderId",OrderId).queryForFirst();
    }

    @Override
    public List<OrderEntity> getSearchOrders(String searchText) throws SQLException {
        return queryBuilder().where().like("orderId",searchText).query();
    }

    @Override
    public void updateRefundedOrderId(String currentOrderId, int returnedOrderId) throws SQLException {
        OrderEntity orderEntity = queryForId(returnedOrderId);
        orderEntity.setRefundedOrderId(currentOrderId);
        update(orderEntity);
    }

    @Override
    public long[] insertAll(OrderEntity... orderEntities) throws SQLException {
        return new long[create(Arrays.asList(orderEntities))];
    }

    @Override
    public void delete() {

    }
}
