package com.webkul.mobikul.mobikulstandalonepos.db.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.Customer;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class CustomerDaoImpl extends BaseDaoImpl<Customer,Integer> implements CustomerDao {

    protected CustomerDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Customer.class);
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        return queryForAll();
    }

    @Override
    public List<Customer> loadAllByIds(int[] CustomerIds) throws SQLException {
        return queryBuilder().where().in("customerId", CustomerIds).query();
    }

    @Override
    public Customer checkEmailExist(String email) throws SQLException {
        return queryBuilder().where().eq("email", email).queryForFirst();
    }

    @Override
    public Customer checkNumberExist(String contactNumber) throws SQLException {
        return queryBuilder().where().eq("contact_number",contactNumber).queryForFirst();
    }

    @Override
    public void updateCustomerById(String firstName, String lastName, String email, String contactNumber, String addressLine, String city, String postalCode, String state, String country, int customerId) throws SQLException {
        Customer customer = queryForId(customerId);
    }

    @Override
    public void insertAll(Customer... Customers) throws SQLException {
        create(Arrays.asList(Customers));
    }

    @Override
    public void delete() {

    }
}
