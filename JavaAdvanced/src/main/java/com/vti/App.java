package com.vti;


import com.vti.controller.DepartmentController;
import com.vti.entity.Department;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        DepartmentController controller=new DepartmentController();
        //lấy ra tất cả
        controller.findAll().forEach(System.out::println);
        //tìm theo id
        Department department=controller.findById(1);
        System.out.println(department );


    }
}
