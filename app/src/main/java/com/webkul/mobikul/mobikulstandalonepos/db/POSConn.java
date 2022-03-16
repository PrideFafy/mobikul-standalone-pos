package com.webkul.mobikul.mobikulstandalonepos.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class POSConn implements Serializable {

    private static volatile POSConn dbInstance;

    private static final String ip = "3.137.147.213";
    private static final String port = "5432";
    private static final String Classes = "org.postgresql.Driver";
    private static final String database = "postgres";
    private static final String username = "postgres";
    private static final String password = "posdbpassword";
    private static final String url = "jdbc:postgresql://"+ip+":"+port+"/"+database;
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
            status = false;
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

            String adminSql = "CREATE TABLE IF NOT EXISTS administrator (uid serial NOT NULL, first_name TEXT, last_name TEXT, email TEXT,username TEXT, password TEXT, PRIMARY KEY(uid))";
           s.addBatch(adminSql);
            String categorySql = "CREATE TABLE IF NOT EXISTS Category (cId INTEGER NOT NULL, category_name TEXT, is_active INTEGER NOT NULL, is_include_in_drawer_menu INTEGER NOT NULL, category_icon INTEGER NOT NULL, level INTEGER NOT NULL, parent_id INTEGER NOT NULL, path TEXT, PRIMARY KEY(cId))";
            s.addBatch(categorySql);
            String productSql = "CREATE TABLE IF NOT EXISTS Product (pId INTEGER NOT NULL, product_name TEXT, product_s_deccription TEXT, sku TEXT, is_enabled INTEGER NOT NULL, price TEXT, special_price TEXT, is_taxable_goods_applied INTEGER NOT NULL" +
                    ",track_inventory INTEGER NOT NULL, quantity TEXT, stock_availability INTEGER NOT NULL, image TEXT, weight TEXT, productCategories TEXT,formatted_price TEXT, formatted_special_price TEXT" +
                    ",barCode TEXT, product_tax TEXT, options TEXT, PRIMARY KEY(pId))";
            s.addBatch(productSql);
            String customerSql = "CREATE TABLE IF NOT EXISTS Customer (customerId INTEGER NOT NULL, customer_first_name TEXT, customer_last_name TEXT, email TEXT, contact_number TEXT" +
                    ", address_line TEXT,city TEXT,postal_code TEXT,state TEXT,country TEXT, PRIMARY KEY(customerId))";
            s.addBatch(customerSql);
            String orderE =  "CREATE TABLE IF NOT EXISTS OrderEntity (orderId INTEGER NOT NULL, time TEXT, date TEXT, cart_data TEXT, qty TEXT" +
                    ", cash_data TEXT,is_synced TEXT,is_return TEXT,refunded_order_id TEXT, PRIMARY KEY(orderId))";
            s.addBatch(orderE);
            String holdCartSql = "CREATE TABLE IF NOT EXISTS HoldCart(holdCartId INTEGER NOT NULL, time TEXT, date TEXT, cart_data TEXT, qty TEXT" +
                    ", is_synced TEXT, PRIMARY KEY(holdCartId))";
            s.addBatch(holdCartSql);

            String drawerSql = "CREATE TABLE IF NOT EXISTS CashDrawerModel (date INTEGER NOT NULL, cash_drawer_items TEXT, opening_balance TEXT, formatted_opening_balance TEXT" +
                    ", closing_balance TEXT, formatted_closing_balance TEXT, net_revenue TEXT, formatted_net_revenue TEXT, " +
                    " in_amount TEXT, formatted_in_amount TEXT, out_amount TEXT, formatted_out_amount TEXT, is_synced TEXT, PRIMARY KEY(date))";
            s.addBatch(drawerSql);
            String optionSql = "CREATE TABLE IF NOT EXISTS Option (optionId INTEGER NOT NULL, option_name TEXT, option_type TEXT, option_values TEXT, sort_order TEXT" +
                    ", PRIMARY KEY(optionId))";
            s.addBatch(optionSql);
            String taxSql = "CREATE TABLE IF NOT EXISTS Tax (taxId INTEGER NOT NULL, tax_name TEXT, is_enabled TEXT, type TEXT, tax_rate TEXT" +
                    ", PRIMARY KEY(taxId))";
            s.addBatch(taxSql);

           s.executeBatch();
            System.out.println(
                    "New db");
           // connection.commit();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
