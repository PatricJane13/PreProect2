package DAO;

import model.User;
import org.hibernate.*;
import util.DBHelper;

import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private SessionFactory sessionFactory = DBHelper.getSessionFactory();

    public UserHibernateDAO() {
    }

    @Override
    public User getUserByName(String name) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("FROM User WHERE name = :name");
            query.setParameter("name", name);
            User user = (User) query.uniqueResult();
            session.close();
            return user;
        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                assert session != null;
                session.close();
            } catch (HibernateException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public User getUserById(Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            User user = (User) session.get(User.class, id);
            transaction.commit();
            session.close();
            return user;
        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                assert session != null;
                session.close();
            } catch (HibernateException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                assert session != null;
                session.close();
            } catch (HibernateException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            List<User> list = session.createQuery("FROM User").list();
            session.close();
            return list;
        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                session.close();
            } catch (HibernateException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void updateUser(User newUser) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("UPDATE User SET name= :newName, password = :newPassword, age = :newAge WHERE id = :id");
            query.setParameter("newName", newUser.getName());
            query.setParameter("newPassword", newUser.getPassword());
            query.setParameter("newAge", newUser.getAge());
            query.setParameter("id", newUser.getId());
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                assert session != null;
                session.close();
            } catch (HibernateException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void deleteUser(Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            User user = getUserById(id);
            session.delete(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                assert session != null;
                session.close();
            } catch (HibernateException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public boolean checkingUser(String name, String password) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM User WHERE name =:name AND password =:password");
            query.setParameter("name", name);
            query.setParameter("password", password);
            User user = (User) query.uniqueResult();
            transaction.commit();
            session.close();
            return user != null;
        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                assert session != null;
                session.close();
            } catch (HibernateException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }
}
