package com.webkul.mobikul.mobikulstandalonepos.db.dao;

import android.arch.persistence.room.Insert;

import com.j256.ormlite.dao.Dao;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.OptionValues;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by aman.gupta on 17/2/18. @Webkul Software Private limited
 */

public interface OptionValuesDao extends Dao<OptionValues,Integer> {

    //@Query("SELECT * FROM OptionValues")
    List<OptionValues> getAll() throws SQLException;

    //@Query("SELECT * FROM OptionValues WHERE option_id IN (:optionsIds)")
    List<OptionValues> loadAllByIds(int[] optionsIds) throws SQLException;

//    @Query("UPDATE OptionValues SET category_name = :categoryName, is_active = :isActive, is_include_in_drawer_menu = :isIncludeInDrawerMenu WHERE cId = :cId")
//    void updateOptionValuesById(String categoryName, boolean isActive, boolean isIncludeInDrawerMenu, int cId);

    @Insert
    Long[] insertAll(OptionValues... OptionValuess) throws SQLException;


  //  @Query("DELETE FROM OptionValues")
    void delete();
}
