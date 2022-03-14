package com.webkul.mobikul.mobikulstandalonepos.db;

import java.sql.Connection;

public class POSConn {

    private static String ip = "3.137.147.213";
    private static String port = "5432";
    private static String Classes = "org.postgresql.Driver";
    private static String database = "CustomerCareSystem";
    private static String username = "postgres";
    private static String password = "posdbpassword";
    private static String url = "jdbc:postgresql://"+ip+":"+port+"/"+database;

    private Connection connection = null;

}
