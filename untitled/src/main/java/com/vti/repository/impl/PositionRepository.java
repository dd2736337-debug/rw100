package com.vti.repository.impl;

import com.vti.entity.Position;
import com.vti.repository.IPosition;
import com.vti.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PositionRepository implements IPosition {
    private final SessionFactory sessionFactory = HibernateUtils.sessionFactory;

    @Override
    public List<Position> findAll() {
        List<Position> positions = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            String hql = "From Position";
            Query<Position> query = session.createQuery(hql, Position.class);
            positions = query.list();
            return positions;

        } finally {
            session.close();
        }

    }

    @Override
    public Position findById(Integer id) {
        Position position = new Position();
        Session session = sessionFactory.openSession();
        try {
            String hql = "From Position Where id = :idParam";
            Query<Position> query = session.createQuery(hql, Position.class);
            query.setParameter("idParam", 1);
            position = query.uniqueResult();
            return position;

        } finally {
            session.close();
        }
    }

    @Override
    public void create(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            Position position = new Position();
            position.setName(name);
            session.persist(position);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public void update(String updateName, Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            Position position = session.find(Position.class, id);
            position.setName(updateName);
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }
}

