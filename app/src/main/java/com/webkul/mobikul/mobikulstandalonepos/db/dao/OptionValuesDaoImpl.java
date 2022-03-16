package com.webkul.mobikul.mobikulstandalonepos.db.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.OptionValues;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class OptionValuesDaoImpl extends BaseDaoImpl<OptionValues,Integer> implements OptionValuesDao {

    protected OptionValuesDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, OptionValues.class);
    }

    @Override
    public List<OptionValues> getAll() throws SQLException {
        return queryForAll();
    }

    @Override
    public List<OptionValues> loadAllByIds(int[] optionsIds) throws SQLException {
        return queryBuilder().where().in("option_id", optionsIds).query();
    }

    @Override
    public Long[] insertAll(OptionValues... OptionValuess) throws SQLException {
        return new Long[create(Arrays.asList(OptionValuess))];
    }

    @Override
    public void delete() {

    }
}
