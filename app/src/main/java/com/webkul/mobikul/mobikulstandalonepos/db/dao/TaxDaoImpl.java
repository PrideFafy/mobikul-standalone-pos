package com.webkul.mobikul.mobikulstandalonepos.db.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.Tax;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class TaxDaoImpl extends BaseDaoImpl<Tax,Integer> implements TaxDao {
    protected TaxDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Tax.class);
    }

    @Override
    public List<Tax> getAll() throws SQLException {
        return queryForAll();
    }

    @Override
    public List<Tax> loadAllByIds(int[] TaxIds) throws SQLException {
        return queryBuilder().where().in("taxId", TaxIds).query();
    }

    @Override
    public List<Tax> getEnabledTax(boolean isEnabled) throws SQLException {
        return queryForEq("enabled", isEnabled);
    }

    @Override
    public void updateTaxById(String TaxName, boolean isActive, String taxRate, int taxId) throws SQLException {
        Tax tax = queryForId(taxId);
        tax.setTaxName(TaxName);
        tax.setTaxRate(taxRate);
        tax.setEnabled(isActive);
        update(tax);
    }

    @Override
    public Long[] insertAll(Tax... Taxs) throws SQLException {
        return new Long[create(Arrays.asList(Taxs))];
    }

    @Override
    public void delete() {

    }
}
