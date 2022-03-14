package com.webkul.mobikul.mobikulstandalonepos.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class POSConn implements Serializable {

    private static volatile POSConn dbInstance;

    private static String ip = "3.137.147.213";
    private static String port = "5432";
    private static String Classes = "org.postgresql.Driver";
    private static String database = "postgres";
    private static String username = "postgres";
    private static String password = "posdbpassword";
    private static String url = "jdbc:postgresql://"+ip+":"+port+"/"+database;
    //private static String url = "jdbc:postgresql://%s:%d/%s";

    private Connection connection = null;

    private static boolean status;



    //private constructor.
    private POSConn(){

        //Prevent form the reflection api.
        if (dbInstance != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static POSConn getPostDB() {
        if (dbInstance == null) { //if there is no instance available... create new one
            synchronized (POSConn.class) {
                if (dbInstance == null) dbInstance = new POSConn();
            }
        }

        //dbInstance.url = String.format(dbInstance.url, dbInstance.ip, dbInstance.port, dbInstance.database);
        dbInstance.connect();

        //this.disconnect();
        System.out.println("connection status:" + status);
        return dbInstance;
    }

    //Make singleton from serialize and deserialize operation.
    protected POSConn readResolve() {
        return getPostDB();
    }

    private void connect()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    Class.forName(Classes);
                    connection = DriverManager.getConnection(url, username, password);
                    status = true;
                    setupDB();
                    System.out.println("connected:" + status);
                }
                catch (Exception e)
                {
                    status = false;
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try
        {
            thread.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            this.status = false;
        }
    }

    public static Connection getExtraConnection()
    {
        Connection c = null;
        try
        {
            Class.forName(Classes);
            c = DriverManager.getConnection(url, username, password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }

    public void setupDB(){

        try {
            Statement s = connection.createStatement();
            String adminSql = "CREATE TABLE IF NOT EXISTS nadministrator (uid serial NOT NULL, first_name TEXT, last_name TEXT, email TEXT,username TEXT, password TEXT, PRIMARY KEY(uid))";
            String seq = "CREATE SEQUENCE nadministrator_id_seq INCREMENT 5 START 10;";
            ResultSet rs = s.executeQuery(seq);
            System.out.println(
                    "New db");
            connection.commit();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
