package com.webkul.mobikul.mobikulstandalonepos.db.ndao;

import com.j256.ormlite.dao.Dao;
import com.webkul.mobikul.mobikulstandalonepos.db.model.NAdministrator;

public interface NAdminDAO extends Dao<NAdministrator, Integer> {

    NAdministrator findByEmailAndPassword(String email, String password);
}
