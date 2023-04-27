package com.javarush.marzhiievskyi.dao;

import com.javarush.marzhiievskyi.entity.FilmText;
import org.hibernate.SessionFactory;

public class FilmTextDAO extends GenericDAO<FilmText> {
    public FilmTextDAO( SessionFactory sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}
