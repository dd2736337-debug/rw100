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
        Department department=controller.findById(1);
        System.out.println(department);

    }
}
