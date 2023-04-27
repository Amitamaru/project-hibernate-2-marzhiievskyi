package com.javarush.marzhiievskyi.dao;

import com.javarush.marzhiievskyi.entity.Store;
import org.hibernate.SessionFactory;

public class StoreDAO extends GenericDAO<Store> {
    public StoreDAO( SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }
}
