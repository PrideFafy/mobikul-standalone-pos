package com.webkul.mobikul.mobikulstandalonepos.db.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.HoldCart;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class HoldCartDaoImpl extends BaseDaoImpl<HoldCart,Integer> implements  HoldCartDao {
    protected HoldCartDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, HoldCart.class);
    }

    @Override
    public List<HoldCart> getAll() throws SQLException {
        return queryForAll();
    }

    @Override
    public List<HoldCart> loadAllByIds(int[] holdCartIds) throws SQLException {
        return queryBuilder().where().in("holdCartId", holdCartIds).query();
    }

    @Override
    public List<HoldCart> getSearchHoldCart(String searchText) throws SQLException {
        return queryBuilder().where().like("holdCartId", searchText).query();
    }

    @Override
    public long[] insertAll(HoldCart... holdCarts) throws SQLException {
        return new long[]{create(Arrays.asList(holdCarts))};
    }

    @Override
    public void delete() {

    }

    @Override
    public void delete(long holdCartId) throws SQLException {
            deleteById((int) holdCartId);
    }
}
