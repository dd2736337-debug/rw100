package com.vti.testtingsystem.controller;

import com.vti.testtingsystem.dto.DepartmentDto;
import com.vti.testtingsystem.entity.Department;
import com.vti.testtingsystem.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    //lấy ra ds department

    @GetMapping
    public ResponseEntity<List<Department>> findAll() {

        return ResponseEntity.ok(departmentService.findAll());
    }

    //lấy ra thông tin department theo id
    @GetMapping("/{id}")
    public ResponseEntity<Department> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(departmentService.findById(id));
    }

    //tạo mới 1 department
    @PostMapping
    public ResponseEntity<String> create(@RequestBody DepartmentDto dto) {
        departmentService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Thêm mới thành công");
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody DepartmentDto dto, @PathVariable Integer id) {
        departmentService.update(dto, id);
        return ResponseEntity.ok("Update thành công");
    }

    //Xóa
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByID(@PathVariable Integer id) {
        departmentService.deleteByID(id);
        return ResponseEntity.ok("Xóa thành công");
    }

//    //tạo mới 1 department
//    @PostMapping
//    public ResponseEntity<String> create(@RequestBody Department department) {
//        departmentService.create(department);
//        return new ResponseEntity<>("Tạo mới thành công", HttpStatus.CREATED);
//    }

//    //xóa theo id
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteByID(@PathVariable Integer id) {
//        departmentService.deleteByID(id);
//        return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
//    }


    //update theo id
//    @PutMapping({"/{id}"})
//    public ResponseEntity<String> update(@RequestBody Department department, @PathVariable Integer id) {
//        departmentService.update(department, id);
//        return new ResponseEntity<>("Update thành công", HttpStatus.OK);
//    }

}
