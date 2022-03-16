package com.webkul.mobikul.mobikulstandalonepos.db.dao;



import com.webkul.mobikul.mobikulstandalonepos.db.entity.Options;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by aman.gupta on 16/2/18. @Webkul Software Private limited
 */

public interface OptionDao extends com.j256.ormlite.dao.Dao<Options,Integer> {

   // @Query("SELECT * FROM Options ORDER BY option_id DESC")
    List<Options> getAll() throws SQLException;

   // @Query("SELECT * FROM Options WHERE option_id IN (:optionsIds)")
    List<Options> loadAllByIds(int[] optionsIds) throws SQLException;

    //@Query("UPDATE Options SET option_name = :optionName, option_type = :optionType, option_values = :optionValues WHERE option_id = :oId")
    void updateOptionsById(String optionName
            , String optionType
            , String optionValues
            , int oId) throws SQLException;

    //@Insert
    Long[] insertAll(Options... Optionss) throws SQLException;


    //@Query("DELETE FROM Options")
    void delete();

}
