package com.javarush.marzhiievskyi.dao;

import com.javarush.marzhiievskyi.entity.Staff;
import org.hibernate.SessionFactory;

public class StaffDAO extends GenericDAO<Staff> {
    public StaffDAO( SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }
}
