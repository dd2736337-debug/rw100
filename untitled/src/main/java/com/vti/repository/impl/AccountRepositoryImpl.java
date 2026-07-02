package com.vti.repository.impl;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IDepartmentRepository;
import com.vti.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class AccountRepositoryImpl implements IAccountRepository {
    private final SessionFactory sessionFactory= HibernateUtils.sessionFactory;
    @Override
    public List<Account> findAll() {
        Session session=sessionFactory.openSession();
        try {
            return session.createQuery("from Account", Account.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public Account findById(Integer id) {
        Session session=sessionFactory.openSession();
        try {
            return session.get(Account.class,id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public void create(Account account) {
        Session session=sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(account);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }

    @Override
    public void update(Account account) {
        Session session=sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.merge(account);
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
               Account acc=session.get(Account.class,id);
               if (acc != null){
                   session.remove(acc);
               }
               session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }finally {
                session.close();
            }
    }

    public static void main(String[] args) {
        AccountRepositoryImpl accountRepository=new AccountRepositoryImpl();
        IDepartmentRepository departmentRepository=new DepartmentRepositoryImpl();
//        List<Account> accounts =accountRepository.findAll();
//        for (Account acc:accounts){
//            System.out.println(acc);
//        }
//        System.out.println(accountRepository.findById(1));

//        Account account =new Account();
//        account.setFullName("demo");
//        account.setEmail("demo@gamil.com");
//        account.setUserName("demo1");
//
//        Department department=departmentRepository.findByID(1);
//        account.setDep(department);
//
//        accountRepository.create(account);

        //update
//        Account account =accountRepository.findById(12);
//        account.setFullName("Đại");
//        account.setUserName("Dương");
//        accountRepository.update(account);

        //Xóa acc có id bằng 20
//        accountRepository.delete(20);

    }

    //lấy ra danh sách account
    //lấy account thì lầy department
    //lấy department -> lấy ra ds account liên quan đến  department đó//n câu sql để lấy ra n ds liên quan
    //lấy ra ds account liên quan đến department đó -> lấy ra ds department
}
