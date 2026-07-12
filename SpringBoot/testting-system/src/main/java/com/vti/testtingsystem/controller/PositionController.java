package com.vti.testtingsystem.controller;

import com.vti.testtingsystem.Enum.PositionName;
import com.vti.testtingsystem.dto.PositionDto;
import com.vti.testtingsystem.form.PositionCreateAndUpdateForm;
import com.vti.testtingsystem.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/positions")
public class PositionController {
    @Autowired
    private IPositionService service;

    @GetMapping
    public ResponseEntity<List<PositionDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PositionDto> findByName(@PathVariable PositionName name) {
        return ResponseEntity.ok(service.findByName(name));
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody PositionCreateAndUpdateForm form) {
        service.create(form);
        return ResponseEntity.status(HttpStatus.CREATED).body("Thêm mới thành công");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody PositionCreateAndUpdateForm form, @PathVariable Integer id) {
        service.update(form, id);
        return ResponseEntity.ok("Update thành công");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok("Xóa thành công");
    }


}
