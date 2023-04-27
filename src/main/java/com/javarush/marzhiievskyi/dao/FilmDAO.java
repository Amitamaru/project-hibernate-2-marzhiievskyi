package com.javarush.marzhiievskyi.dao;

import com.javarush.marzhiievskyi.entity.Film;
import org.hibernate.SessionFactory;

public class FilmDAO extends GenericDAO<Film> {
    public FilmDAO( SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }
}
