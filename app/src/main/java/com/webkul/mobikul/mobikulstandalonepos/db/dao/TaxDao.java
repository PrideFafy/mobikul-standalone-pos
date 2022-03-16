package com.webkul.mobikul.mobikulstandalonepos.db.dao;

import com.j256.ormlite.dao.Dao;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.Tax;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by aman.gupta on 27/2/18. @Webkul Software Private limited
 */

public interface TaxDao extends Dao<Tax,Integer> {
   // @Query("SELECT * FROM Tax ORDER BY taxId DESC")
    List<Tax> getAll() throws SQLException;

   // @Query("SELECT * FROM Tax WHERE taxId IN (:TaxIds)")
    List<Tax> loadAllByIds(int[] TaxIds) throws SQLException;

    //@Query("SELECT * FROM Tax WHERE is_enabled = :isEnabled")
    List<Tax> getEnabledTax(boolean isEnabled) throws SQLException;

    //@Query("UPDATE Tax SET tax_name = :TaxName, is_enabled = :isActive, tax_rate = :taxRate WHERE taxId = :taxId")
    void updateTaxById(String TaxName, boolean isActive, String taxRate, int taxId) throws SQLException;

   // @Insert
    Long[] insertAll(Tax... Taxs) throws SQLException;


    //@Query("DELETE FROM Tax")
    void delete();

}
