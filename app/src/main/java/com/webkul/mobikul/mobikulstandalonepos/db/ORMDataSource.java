package com.webkul.mobikul.mobikulstandalonepos.db;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class ORMDataSource {
    private static ORMDataSource ormDataSource;
    private static final String ip = "3.137.147.213";
    private static final String port = "5432";
    private static final String Classes = "org.postgresql.Driver";
    private static final String database = "postgres";
    private static final String username = "postgres";
    private static final String password = "posdbpassword";
    // private static String url = "jdbc:postgresql://"+ip+":"+port+"/"+database;
    private static String url = "jdbc:postgresql://"+ip+":"+port+"/"+database;

    public static ConnectionSource createDataSource () throws SQLException {
        // ConnectionFactory can handle null username and password (for local host-based authentication)

        // Disabling auto-commit on the connection factory confuses ORMLite, so we leave it on.
        // In any case ORMLite will create transactions for batch operations.
        url = String.format(url, ip, port, database);

           return new JdbcConnectionSource(url,username,password);

    }





}
