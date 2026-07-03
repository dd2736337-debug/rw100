package com.vti.repository.impl;

import com.vti.entity.GroupAccount;
import com.vti.repository.IGroupAccount;
import com.vti.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class GruopAccountRepository implements IGroupAccount {
    private  final SessionFactory sessionFactory = HibernateUtils.sessionFactory;

    @Override
    public List<GroupAccount> findAll() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("FROM GroupAccount ", GroupAccount.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public GroupAccount findById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(GroupAccount.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public void create(GroupAccount groupAccount) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(groupAccount);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public void update(GroupAccount groupAccount) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.merge(groupAccount);
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
            GroupAccount groupAccount = session.get(GroupAccount.class, id);
            if (groupAccount != null) {
                session.remove(groupAccount);
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
