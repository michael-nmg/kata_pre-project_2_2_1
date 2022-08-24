package hiber.dao;

import hiber.model.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;
    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from User", User.class)
                .getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsersByCar(String model, Integer series) {
        return sessionFactory
                .getCurrentSession()
                .createQuery(
                        "select usr from User usr where usr.car.model = :model and usr.car.series = :series",
                        User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .getResultList();
    }
}
