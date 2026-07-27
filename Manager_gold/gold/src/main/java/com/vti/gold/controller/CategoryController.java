package com.vti.gold.controller;

import com.vti.gold.dto.CategoryDTO;
import com.vti.gold.form.CategoryCreateForm;
import com.vti.gold.form.CategoryUpdateForm;
import com.vti.gold.service.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public Page<CategoryDTO> findAll(
            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "id",
                    direction = Sort.Direction.DESC
            )
            Pageable pageable) {

        return categoryService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public CategoryDTO findById(
            @PathVariable Integer id) {

        return categoryService.findById(id);
    }

    @PostMapping
    public void create(
            @Valid @RequestBody CategoryCreateForm form) {

        categoryService.create(form);
    }

    @PutMapping("/{id}")
    public void update(
            @PathVariable Integer id,
            @Valid @RequestBody CategoryUpdateForm form) {

        categoryService.update(id, form);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Integer id) {

        categoryService.delete(id);
    }


}
