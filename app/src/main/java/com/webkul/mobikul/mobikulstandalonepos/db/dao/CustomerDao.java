package com.webkul.mobikul.mobikulstandalonepos.db.dao;


import com.j256.ormlite.dao.Dao;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.Customer;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by aman.gupta on 23/1/18. @Webkul Software Private limited
 */

public interface CustomerDao extends Dao<Customer, Integer> {
    //@Query("SELECT * FROM Customer")
    List<Customer> getAll() throws SQLException;

    //@Query("SELECT * FROM Customer WHERE customerId IN (:CustomerIds)")
    List<Customer> loadAllByIds(int[] CustomerIds) throws SQLException;

    //@Query("SELECT * FROM Customer WHERE email IN (:email)")
    Customer checkEmailExist(String email) throws SQLException;

    //@Query("SELECT * FROM Customer WHERE contact_number IN (:contactNumber)")
    Customer checkNumberExist(String contactNumber) throws SQLException;

   // @Query("UPDATE Customer SET customer_first_name = :firstName, customer_last_name = :lastName, email = :email, contact_number = :contactNumber, address_line = :addressLine, city = :city, postal_code = :postalCode, state = :state, country = :country WHERE customerId = :customerId")
    void updateCustomerById(String firstName, String lastName, String email, String contactNumber, String addressLine, String city, String postalCode, String state, String country, int customerId) throws SQLException;

    //@Insert
    void insertAll(Customer... Customers) throws SQLException;

    //@Delete
    //void delete(Customer Customer);

    //@Query("DELETE FROM Customer")
    void delete();

}
