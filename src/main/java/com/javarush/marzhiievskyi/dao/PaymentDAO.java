package com.javarush.marzhiievskyi.dao;

import com.javarush.marzhiievskyi.entity.Payment;
import org.hibernate.SessionFactory;

public class PaymentDAO extends GenericDAO<Payment> {
    public PaymentDAO( SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}
