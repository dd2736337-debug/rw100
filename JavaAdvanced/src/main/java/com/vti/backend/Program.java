package com.vti.backend;

import com.vti.controller.AccountController;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.enums.PositionName;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        //lấy ds department từ DB
        //B1:tạo session kết nối đến database
//        SessionFactory sessionFactory;
//        Configuration cfg = new Configuration();
//        cfg.configure();
//
//
//        sessionFactory = cfg.buildSessionFactory();
//
//        //kết nối đến database
//        Session session = sessionFactory.openSession();
//
//        //b2:Lấy dữ liệu từ bảng department
//
//        List<Department> departments = new ArrayList<>();
//
//        String hql = "From Department";
//
//        Query<Department> query = session.createQuery(hql, Department.class);
//
//        departments = query.list(); //lấy ds
//
//        for (Department de : departments) {
//            System.out.println(de.toString());
//        }

//        tìm kiếm theo id

//        String hql="From Department Where id = :idParam";
//        Query<Department> query=session.createQuery(hql,Department.class);
//        query.setParameter("idParam",1);
//        Department department=query.uniqueResult();
//        System.out.println(department);
//

        //thêm mới 1 department
        //mở transaction rồi comit
//        session.beginTransaction();
//        try {
//            Department department = new Department();
//            department.setName("Demo123");
//            session.persist(department);
//
//            session.getTransaction().commit();
//
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//        }

        //update tên departmentName5 cho department có id =5

        //b1:tìm department có id =5

//        String hql="From Department Where id = :idParam";
//        Query<Department> query=session.createQuery(hql,Department.class);
//        query.setParameter("idParam",5);
//        Department department=query.uniqueResult();

        //b2: update thông tin cho department trên
//        session.beginTransaction();
//        department.setName("departmentName5");
//        session.getTransaction().commit();

        //thêm mới
        AccountController controller=new AccountController();

//        Department department=new Department();
//        department.setName("IT");
//
//        Position position =new Position();
//        position.setName(PositionName.DEV);
//
//        Account account =new Account();
//        account.setUserName("Duong01");
//        account.setFullName("Đại Dương");
//        account.setEmail("daiduong2593@gmail.com");
//        account.setDep(department);
//        account.setPosition(position);
//
//        controller.create(account);
//        System.out.println("Thêm mới thành công");
//        System.out.println(account);

        //Lấy danh sách
//        List<Account> accounts=controller.findAll();
//        for (Account account:accounts){
//            System.out.println(account);
//        }

        //Tìm theo id
//        Account account=controller.findById(3);
//        System.out.println(account);

        //update
//        Account account=controller.findById(3);
//        if (account!=null){
//            account.setFullName("Dương Đại");
//            controller.update(account);
//            System.out.println("Cập nhật thành công");
//        }else {
//            System.out.println("Không tìm thấy accout");
//        }

        //delete
        Account account=controller.findById(1);
        if (account != null){
            controller.delete(1);
            System.out.println("Xóa thành công");
        }else {
            System.out.println("Không tìm thấy account");
        }





    }
}
