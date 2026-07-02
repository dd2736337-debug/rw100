package com.vti.repository.impl;

import com.vti.entity.Department;
import com.vti.repository.IDepartmentRepository;
import com.vti.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class DepartmentRepositoryImpl implements IDepartmentRepository {
    private final SessionFactory sessionFactory = HibernateUtils.sessionFactory;

    @Override
    public List<Department> findAll() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from Department ", Department.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public Department findByID(Integer id) {
       Session session=sessionFactory.openSession();
       try {
           return session.get(Department.class,id);
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }finally {
           session.close();
       }
    }

    @Override
    public void create(Department department) {
        Session session=sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(department);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }

    @Override
    public void update(Department department) {
        Session session=sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.merge(department);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }
    @Override
    public void delete(Integer id) {
        Session session=sessionFactory.openSession();
        try {
            session.beginTransaction();
            Department d=session.get(Department.class,id);
            if(d!=null){
                session.remove(d);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }

    }

//    @Override
//    public List<Department> findAll() {
//        List<Department> departments = new ArrayList<>();
//        Session session = sessionFactory.openSession();
//        try {
//
//            String hql = "From Department";
//
//            Query<Department> query = session.createQuery(hql, Department.class);
//
//            departments = query.list(); //lấy ds
//
//
//            return departments;
//
//        } finally {
//            session.close();
//        }
//
//    }
//
//    @Override
//    public Department findByID(Integer id) {
//        Department department = new Department();
//        Session session = sessionFactory.openSession();
//        try {
//            String hql = "From Department Where id = :idParam";
//            Query<Department> query = session.createQuery(hql, Department.class);
//            query.setParameter("idParam", 1);
//            department = query.uniqueResult();
//            return department;
//
//        } finally {
//            session.close();
//        }
//
//    }
//
//    @Override
//    public void create(String name) {
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        try {
//            Department department = new Department();
//            department.setName(name);
//            session.persist(department);
//
//            session.getTransaction().commit();
//
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//        } finally {
//            session.close();
//        }
//
//    }
//
//    @Override
//    public void update(String updateName, Integer id) {
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        try {
//            //tìm ra department cần update
////            Department department = this.findByID(id); //cách 1
//
//            Department department = session.find(Department.class, id); //cách 2
//
//            department.setName(updateName);
//
//
//            session.getTransaction().commit();
//
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//        } finally {
//            session.close();
//        }
//
//    }
}
