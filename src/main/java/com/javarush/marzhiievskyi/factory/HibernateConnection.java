package com.javarush.marzhiievskyi.factory;

import com.javarush.marzhiievskyi.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static java.util.Objects.isNull;

public class HibernateConnection {
    private static HibernateConnection instance;

    private final SessionFactory sessionFactory;

    public HibernateConnection() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionfactory() {
        if (isNull(instance)) {
            instance = new HibernateConnection();
        }

        return instance.sessionFactory;
    }
}
