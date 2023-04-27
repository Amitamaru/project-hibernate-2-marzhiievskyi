package com.javarush.marzhiievskyi.dao;

import com.javarush.marzhiievskyi.entity.Rental;
import org.hibernate.SessionFactory;

public class RentalDAO extends GenericDAO<Rental> {
    public RentalDAO( SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }
}
