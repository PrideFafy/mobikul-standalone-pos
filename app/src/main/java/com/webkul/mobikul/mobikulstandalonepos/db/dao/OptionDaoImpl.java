package com.webkul.mobikul.mobikulstandalonepos.db.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.Options;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class OptionDaoImpl extends BaseDaoImpl<Options,Integer> implements OptionDao {

    protected OptionDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Options.class);
    }

    @Override
    public List<Options> getAll() throws SQLException {
        return queryForAll();
    }

    @Override
    public List<Options> loadAllByIds(int[] optionsIds) throws SQLException {
        return queryBuilder().where().in("option_id", optionsIds).query();
    }

    @Override
    public void updateOptionsById(String optionName, String optionType, String optionValues, int oId) throws SQLException {
        Options options = queryForId(oId);
        options.setOptionName(optionName);
        options.setType(optionType);

        update(options);
    }

    @Override
    public Long[] insertAll(Options... Optionss) throws SQLException {
        return new Long[create(Arrays.asList(Optionss))];
    }

    @Override
    public void delete() {

    }
}
