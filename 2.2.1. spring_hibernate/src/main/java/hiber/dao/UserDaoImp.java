package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

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
    public List<User> listUsers(String model, Integer series) {
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
