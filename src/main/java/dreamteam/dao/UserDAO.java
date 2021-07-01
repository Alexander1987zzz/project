package dreamteam.dao;

import dreamteam.dto.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Repository
public class UserDAO {

    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();


    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> users = session.createQuery("FROM User", User.class).getResultList();
        session.close();

        return users;
    }

    public User saveUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            int userId = (Integer) session.save(user);
            session.getTransaction().commit();
            if (userId != 0){
                return new User(userId,user.getName(),user.getSurname(),user.getAge(),user.getEmail());
            }

        } catch (HibernateException e) {// TODO: check email, id
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
            session.close();
        }
        return user;
    }

    public boolean updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return false;
    }

    public boolean deleteUser(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return false;
    }
}


