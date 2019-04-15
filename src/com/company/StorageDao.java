package com.company;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class StorageDao implements Storage {

private SessionFactory sessionFactory;

    public StorageDao() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void close() {
        sessionFactory.close();
    }

    @Override
    public void removeAll() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = String.format("DELETE FROM %s", User.class.getSimpleName());
            Query query = session.createQuery(hql);
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void removeUser(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = String.format("DELETE FROM %s WHERE _id = %d", User.class.getSimpleName(), id);
            Query query = session.createQuery(hql);
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void removeUserByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = String.format("DELETE FROM %s WHERE name = ('%s')", User.class.getSimpleName(), name);
            Query query = session.createQuery(hql);
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void addUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }

    @Override
    public void updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }
    }

    @Override
    public User getUser(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("FROM User WHERE _id = :id ", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User ORDER BY _id", User.class).list();
        }
    }
}
