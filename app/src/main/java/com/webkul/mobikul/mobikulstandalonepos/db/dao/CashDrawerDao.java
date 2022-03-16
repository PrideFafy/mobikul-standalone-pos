package com.webkul.mobikul.mobikulstandalonepos.db.dao;

import com.j256.ormlite.dao.Dao;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.CashDrawerModel;

import java.sql.SQLException;
import java.util.List;



public interface CashDrawerDao extends Dao<CashDrawerModel, String> {
   // @Query("SELECT * FROM CashDrawerModel ORDER BY date DESC")
    List<CashDrawerModel> getAll() throws SQLException;

    //@Query("SELECT * FROM CashDrawerModel WHERE date IN (:date)")
    CashDrawerModel loadAllByDate(String date) throws SQLException;

//    @Query("UPDATE CashDrawerModel SET closing_balance = :closingBalance, net_revenue = :netRevenue, in_amount = :inAmount, out_amount = :outAmount" +
//            ", cash_drawer_items = :cashDrawerItemList, formatted_closing_balance = :formattedClosingBalance, formatted_net_revenue = :formattedNetRevenue" +
//            ", formatted_in_amount = :formattedInAmount, formatted_out_amount = :formattedOutAmount WHERE date = :date")
    void updateCashDrawerModelByDate(String closingBalance, String netRevenue, String inAmount, String outAmount, String cashDrawerItemList, String formattedClosingBalance
            , String formattedNetRevenue, String formattedInAmount, String formattedOutAmount, String date) throws SQLException;

    //@Insert
    void insertAll(CashDrawerModel... CashDrawerModels) throws SQLException;

    //@Delete


    //@Query("DELETE FROM CashDrawerModel")
    void delete();
}
