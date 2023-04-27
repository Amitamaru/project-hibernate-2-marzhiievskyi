package com.javarush.marzhiievskyi.dao;

import com.javarush.marzhiievskyi.entity.Address;
import org.hibernate.SessionFactory;

public class AddressDAO extends GenericDAO<Address> {
    public AddressDAO(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }
}
