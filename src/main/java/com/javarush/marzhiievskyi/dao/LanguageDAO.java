package com.javarush.marzhiievskyi.dao;

import com.javarush.marzhiievskyi.entity.Language;
import org.hibernate.SessionFactory;

public class LanguageDAO extends GenericDAO<Language> {
    public LanguageDAO(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }
}
