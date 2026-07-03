package com.vti.repository.impl;

import com.vti.entity.Group;
import com.vti.repository.IGroup;
import com.vti.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class GroupRepository implements IGroup {
    private final SessionFactory sessionFactory = HibernateUtils.sessionFactory;

    @Override
    public List<Group> findAll() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("FROM Group ", Group.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public Group findById(Integer Id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Group.class, Id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public void create(Group group) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(group);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public void update(Group group) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.merge(group);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Integer Id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Group group = session.get(Group.class, Id);
            if (group != null) {
                session.remove(group);
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
