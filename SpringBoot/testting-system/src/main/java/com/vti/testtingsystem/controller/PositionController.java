package com.vti.testtingsystem.controller;

import com.vti.testtingsystem.dto.PositionDto;
import com.vti.testtingsystem.entity.Position;
import com.vti.testtingsystem.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {
    @Autowired
    private IPositionService service;

    @GetMapping
    public ResponseEntity<List<Position>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody PositionDto dto) {
        service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Thêm mới thành công");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody PositionDto dto, @PathVariable Integer id) {
        service.update(dto, id);
        return ResponseEntity.ok("Update thành công");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok("Xóa thành công");
    }


}
