package com.vti.gold.service.Impl;

import com.vti.gold.dto.CategoryDTO;
import com.vti.gold.entity.Category;
import com.vti.gold.form.CategoryCreateForm;
import com.vti.gold.form.CategoryUpdateForm;
import com.vti.gold.repository.CategoryRepository;
import com.vti.gold.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<CategoryDTO> findAll(Pageable pageable) {
        Page<Category> page = categoryRepository.findAll(pageable);

        return page.map(category -> modelMapper.map(category, CategoryDTO.class));
    }

    @Override
    public CategoryDTO findById(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found!"));

        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public void create(CategoryCreateForm form) {
        if (categoryRepository.existsByName(form.getName())) {
            throw new RuntimeException("Category đã tồn tại!");
        }
        Category category = modelMapper.map(form, Category.class);
        categoryRepository.save(category);
    }

    @Override
    public void update(Integer id, CategoryUpdateForm form) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found!"));
        category.setName(form.getName());
        categoryRepository.save(category);

    }

    @Override
    public void delete(Integer id) {
        Category category =
                categoryRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Category not found!"
                                ));
        categoryRepository.deleteById(id);


    }
}
