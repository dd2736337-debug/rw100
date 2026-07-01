package com.vti.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    public static final SessionFactory sessionFactory= new Configuration().configure().buildSessionFactory();


//    //tạo sessionfatory
//    static {
//        Configuration cfg = new Configuration();
//        cfg.configure();
//
//        sessionFactory = cfg.buildSessionFactory();
//
//        //kết nối đến database
//        Session session = sessionFactory.openSession();
//
//    }


}
