package com.webkul.mobikul.mobikulstandalonepos.db.ndao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.webkul.mobikul.mobikulstandalonepos.db.model.NAdministrator;

import java.sql.SQLException;

public class NAdminDAOImpl extends BaseDaoImpl<NAdministrator, Integer> implements NAdminDAO{

    protected NAdminDAOImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, NAdministrator.class);
    }

    @Override
    public NAdministrator findByEmailAndPassword(String email, String password)  {

        try {
            QueryBuilder<NAdministrator, Integer> qb = super.queryBuilder();
            qb.limit(1L).where().eq("email", email).eq("password",password);
            return qb.queryForFirst();
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;//super.queryBuilder().;
    }
}
