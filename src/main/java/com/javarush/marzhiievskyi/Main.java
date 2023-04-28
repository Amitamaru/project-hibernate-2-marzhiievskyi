package com.javarush.marzhiievskyi;

import com.javarush.marzhiievskyi.dao.*;
import com.javarush.marzhiievskyi.entity.*;
import com.javarush.marzhiievskyi.factory.HibernateConnection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
        main.customerReturnInventory();
        main.customerRentInventory(customer);

        Film film = main.wasMadeNewFim();

    }

    private Film wasMadeNewFim() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Language language = languageDAO.getItems(0, 10).stream().unordered().findAny().get();
            List<Category> categories = categoryDAO.getItems(0, 3);
            List<Actor> actors = actorDAO.getItems(0, 30);

            Film film = new Film();
            film.setActors(new HashSet<>(actors));
            film.setRating(Rating.PG13);
            film.setCategories(new HashSet<>(categories));
            film.setLanguage(language);
            film.setDescription("Description about new comedy film");
            film.setSpecialFeatures(Set.of(Feature.BEHIND_THE_SCENES, Feature.TRAILERS, Feature.DELETED_SCENES));
            film.setLength((short) 123);
            film.setTitle("Comedy film");
            film.setReplacementCost(BigDecimal.valueOf(19.99));
            film.setRentalRate(BigDecimal.ZERO);
            film.setRentalDuration((byte) 5);
            film.setOriginalLanguage(language);
            film.setYear(Year.now());
            filmDAO.save(film);

            FilmText filmText = new FilmText();
            filmText.setFilm(film);
            filmText.setDescription("Description about new comedy film");
            filmText.setTitle("Comedy film");
            filmTextDAO.save(filmText);

            session.getTransaction().commit();
            return film;
        }
    }

    private void customerRentInventory(Customer customer) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Film film = filmDAO.getFirstAvalibleFilm();
            Store store = storeDAO.getItems(0, 1).get(0);

            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(store);
            inventoryDAO.save(inventory);

            Staff staffManager = store.getStaffManager();

            Rental rental = new Rental();
            rental.setRentalDate(LocalDateTime.now());
            rental.setCustomer(customer);
            rental.setInventory(inventory);
            rental.setStaff(staffManager);
            rentalDAO.save(rental);

            Payment payment = new Payment();
            payment.setRental(rental);
            payment.setCustomer(customer);
            payment.setStaff(staffManager);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setAmount(BigDecimal.valueOf(3.99));
            paymentDAO.save(payment);

            session.getTransaction().commit();
        }
    }

    private void customerReturnInventory() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Rental rental = rentalDAO.getSomeUnreturnedInventor();
            rental.setReturnDate(LocalDateTime.now());
            rentalDAO.save(rental);

            session.getTransaction().commit();
        }
    }

    private Customer createCustomer() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Store store = storeDAO.getItems(0, 1).get(0);
            City city = cityDAO.getByName("Konotop");

            Address address = new Address();
            address.setAddress("Albe 33 str.");
            address.setCity(city);
            address.setDistrict("ua");
            address.setPhone("+3-8-097-8981723");

            addressDAO.save(address);

            Customer customer = new Customer();
            customer.setStore(store);
            customer.setFirstName("Dimka");
            customer.setLastName("Mar");
            customer.setAddress(address);
            customer.setIsActive(true);
            customer.setEmail("desmont@gmail.com");

            customerDAO.save(customer);

            session.getTransaction().commit();
            return customer;
        }
    }
}
