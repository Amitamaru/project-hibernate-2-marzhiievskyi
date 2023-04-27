package com.javarush.marzhiievskyi.dao;

import com.javarush.marzhiievskyi.entity.Customer;
import org.hibernate.SessionFactory;

public class CustomerDAO extends GenericDAO<Customer> {
    public CustomerDAO( SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }
}
