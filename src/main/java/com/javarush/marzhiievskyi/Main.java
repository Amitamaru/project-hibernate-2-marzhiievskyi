package com.javarush.marzhiievskyi;

import com.javarush.marzhiievskyi.dao.*;
import com.javarush.marzhiievskyi.entity.*;
import com.javarush.marzhiievskyi.factory.HibernateConnection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class Main {
    private final SessionFactory sessionFactory = HibernateConnection.getSessionfactory();

    private final ActorDAO actorDAO;
    private final AddressDAO addressDAO;
    private final CategoryDAO categoryDAO;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;
    private final CustomerDAO customerDAO;
    private final FilmDAO filmDAO;
    private final FilmTextDAO filmTextDAO;
    private final InventoryDAO inventoryDAO;
    private final LanguageDAO languageDAO;
    private final PaymentDAO paymentDAO;
    private final RentalDAO rentalDAO;
    private final StaffDAO staffDAO;
    private final StoreDAO storeDAO;

    public Main() {

        //ACTUAL PROPERTIES!!!
        //DELETE AFTER FINISH PROJECT


//        Properties properties = new Properties();
//
//        properties.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
//        properties.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3306/movie");
//        //=============================================================================
//        //change to your user and password DB
//        properties.put(Environment.USER, "root");
//        properties.put(Environment.PASS, "mysql");
//        //=============================================================================
//        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
//        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//        properties.put(Environment.SHOW_SQL, "true");
//        properties.put(Environment.HBM2DDL_AUTO, "validate");

        actorDAO = new ActorDAO(sessionFactory);
        addressDAO = new AddressDAO(sessionFactory);
        categoryDAO = new CategoryDAO(sessionFactory);
        cityDAO = new CityDAO(sessionFactory);
        countryDAO = new CountryDAO(sessionFactory);
        customerDAO = new CustomerDAO(sessionFactory);
        filmDAO = new FilmDAO(sessionFactory);
        filmTextDAO = new FilmTextDAO(sessionFactory);
        inventoryDAO = new InventoryDAO(sessionFactory);
        languageDAO = new LanguageDAO(sessionFactory);
        paymentDAO = new PaymentDAO(sessionFactory);
        rentalDAO = new RentalDAO(sessionFactory);
        staffDAO = new StaffDAO(sessionFactory);
        storeDAO = new StoreDAO(sessionFactory);
    }

    public static void main(String[] args) {
        Main main = new Main();
        Customer customer = main.createCustomer();

    }

    private Customer createCustomer() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Store store = storeDAO.getItems(0, 1).get(0);
            City city = cityDAO.getByName("Konotop");
            Address address = new Address();
            address.setAddress("Myloslavska 33 str.");
            address.setCity(city);
            address.setDistrict("ua");
            address.setPhone("+3-8-097-8981723");

            addressDAO.save(address);

            Customer customer = new Customer();
            customer.setStore(store);
            customer.setFirstName("Amitamaru");
            customer.setLastName("Desmont");
            customer.setAddress(address);
            customer.setIsActive(true);
            customer.setEmail("amitamaru_desmont@gmail.com");

            customerDAO.save(customer);

            session.getTransaction().commit();
            return customer;
        }
    }
}
