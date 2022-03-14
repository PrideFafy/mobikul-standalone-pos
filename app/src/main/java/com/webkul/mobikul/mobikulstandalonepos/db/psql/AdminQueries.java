package com.webkul.mobikul.mobikulstandalonepos.db.psql;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.webkul.mobikul.mobikulstandalonepos.db.POSConn;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.Administrator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminQueries {


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static  List<Administrator> getAll (){

        List<Administrator> administrators = new ArrayList<>();
        try (Connection con = POSConn.getExtraConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Administrator")) {

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(AdminQueries.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return  administrators;

    }
}
