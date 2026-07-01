package com.vti.repository.impl;

import com.vti.entity.Position;
import com.vti.repository.IPosition;
import com.vti.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PositionRepositoryImpl implements IPosition {
    private final SessionFactory sessionFactory = HibernateUtils.sessionFactory;

    @Override
    public List<Position> findAll() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("FROM Position ", Position.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }

    }

    @Override
    public Position findById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Position.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public void create(Position position) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(position);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public void update(Position position) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.merge(position);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Position p = session.get(Position.class, id);
            if (p != null) {
                session.remove(p);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }
}
