package com.javarush.marzhiievskyi.dao;

import com.javarush.marzhiievskyi.entity.Inventory;
import org.hibernate.SessionFactory;

public class InventoryDAO extends GenericDAO<Inventory> {
    public InventoryDAO( SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }
}
