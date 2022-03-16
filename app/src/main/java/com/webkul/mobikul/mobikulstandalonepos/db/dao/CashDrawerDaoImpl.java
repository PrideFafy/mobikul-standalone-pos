package com.webkul.mobikul.mobikulstandalonepos.db.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.CashDrawerModel;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class CashDrawerDaoImpl extends BaseDaoImpl<CashDrawerModel, String> implements  CashDrawerDao{

    protected CashDrawerDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, CashDrawerModel.class);
    }

    @Override
    public List<CashDrawerModel> getAll() throws SQLException {
        return queryForAll();
    }

    @Override
    public CashDrawerModel loadAllByDate(String date) throws SQLException {
        return queryBuilder().where().in("date", date).queryForFirst();
    }

    @Override
    public void updateCashDrawerModelByDate(String closingBalance, String netRevenue, String inAmount, String outAmount,
                                            String cashDrawerItemList, String formattedClosingBalance, String formattedNetRevenue,
                                            String formattedInAmount, String formattedOutAmount, String date) throws SQLException {
        CashDrawerModel model = queryForId(date);
        model.setClosingBalance(closingBalance);
        model.setNetRevenue(netRevenue);
        model.setInAmount(inAmount);
        model.setFormattedClosingBalance(formattedClosingBalance);
        model.setFormattedNetRevenue(formattedNetRevenue);
        model.setFormattedInAmount(formattedInAmount);
        model.setFormattedOutAmount(formattedOutAmount);
        //List<CashDrawerItems> items = Arrays.asList(cashDrawerItemList.split(","));
        update(model);
    }

    @Override
    public void insertAll(CashDrawerModel... CashDrawerModels) throws SQLException {
        create(Arrays.asList(CashDrawerModels));
    }

    @Override
    public void delete() {

    }
}
