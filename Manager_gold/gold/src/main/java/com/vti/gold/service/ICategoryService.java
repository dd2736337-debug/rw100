package com.vti.gold.service;

import com.vti.gold.dto.CategoryDTO;
import com.vti.gold.form.CategoryCreateForm;
import com.vti.gold.form.CategoryUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService {
    Page<CategoryDTO> findAll(Pageable pageable);

    CategoryDTO findById(Integer id);

    void create(CategoryCreateForm form);

    void update(Integer id, CategoryUpdateForm form);

    void delete(Integer id);
}
