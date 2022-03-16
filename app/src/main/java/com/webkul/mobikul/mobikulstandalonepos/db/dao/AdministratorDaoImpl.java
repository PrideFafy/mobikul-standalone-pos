package com.webkul.mobikul.mobikulstandalonepos.db.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.Administrator;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class AdministratorDaoImpl extends BaseDaoImpl<Administrator, Integer> implements AdministratorDao{

    protected AdministratorDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Administrator.class);
    }

    @Override
    public Administrator getAll() throws SQLException {
        return super.queryForFirst();
    }

    @Override
    public List<Administrator> loadAllByIds(int[] AdministratorIds) throws SQLException {
        return super.queryBuilder().where().in("uid", AdministratorIds).query();
    }

    @Override
    public Administrator findByEmail(String email, String password) {
        QueryBuilder<Administrator, Integer> qb = super.queryBuilder();
       // Where<Administrator,Integer> cols = qb.where();
        try {
            return qb.where().eq("email", email).and().eq("password",password).queryForFirst();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateAdminById(String firstName, String lastName, String username, int uId) throws SQLException {

        Dao<Administrator, Integer> administratorDao
                = DaoManager.createDao(connectionSource, Administrator.class);
        Administrator administrator = administratorDao.queryForId(uId);
        administrator.setFirstName(firstName);
        administrator.setLastName(lastName);
        administrator.setUsername(username);


        administratorDao.update(administrator);
    }

    @Override
    public void insertAll(Administrator... Administrators) throws SQLException {
        Dao<Administrator, Integer> administratorDao
                = DaoManager.createDao(connectionSource, Administrator.class);
        administratorDao.create(Arrays.asList(Administrators));

    }
}
