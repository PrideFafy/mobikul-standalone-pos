package com.webkul.mobikul.mobikulstandalonepos.db.dao;


import com.j256.ormlite.dao.Dao;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.Administrator;

import java.sql.SQLException;
import java.util.List;


public interface AdministratorDao extends Dao<Administrator, Integer> {
    //@Query("SELECT * FROM Administrator")
    Administrator getAll() throws SQLException;

   // @Query("SELECT * FROM Administrator WHERE uid IN (:AdministratorIds)")
    List<Administrator> loadAllByIds(int[] AdministratorIds) throws SQLException;

//    @Query("SELECT * FROM Administrator WHERE email LIKE :email " +
//            "AND password LIKE :password")
    Administrator findByEmail(String email, String password);

    //@Query("UPDATE Administrator SET first_name = :firstName, last_name = :lastName, username = :username WHERE uid = :uId")
    void updateAdminById(String firstName, String lastName, String username, int uId) throws SQLException;

    //@Insert
    void insertAll(Administrator... Administrators) throws SQLException;

    //@Delete
    //void delete(Administrator Administrator);
}