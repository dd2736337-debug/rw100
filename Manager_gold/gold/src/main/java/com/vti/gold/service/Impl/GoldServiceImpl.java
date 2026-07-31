package com.vti.gold.service.Impl;


import com.vti.gold.Specification.GoldSpecification;
import com.vti.gold.dto.GoldDTO;
import com.vti.gold.entity.Category;
import com.vti.gold.entity.Gold;
import com.vti.gold.form.GoldCreateForm;
import com.vti.gold.form.GoldUpdateForm;
import com.vti.gold.repository.CategoryRepository;
import com.vti.gold.repository.GoldRepository;
import com.vti.gold.repository.OrderDetailRepository;
import com.vti.gold.service.FileStorageService;
import com.vti.gold.service.IGoldService;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class GoldServiceImpl implements IGoldService {


    @Autowired
    private GoldRepository goldRepository;


    @Autowired
    private CategoryRepository categoryRepository;


    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private FileStorageService fileStorageService;


    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Override
    public Page<GoldDTO> findAll(String name, String type, Pageable pageable) {


        Specification<Gold> specification = Specification.allOf(GoldSpecification.hasName(name), GoldSpecification.hasType(type));


        return goldRepository.findAll(specification, pageable).map(this::convertToDTO);

    }


    @Override
    public GoldDTO findById(Integer id) {


        Gold gold = goldRepository.findById(id).orElseThrow(() -> new RuntimeException("Gold not found!"));


        return convertToDTO(gold);

    }


    @Override
    @Transactional
    public GoldDTO create(GoldCreateForm form) {


        Category category = categoryRepository.findById(form.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found!"));


        Gold gold = new Gold();


        gold.setName(form.getName());

        gold.setType(form.getType());

        gold.setWeight(form.getWeight());

        gold.setPrice(form.getPrice());

        gold.setQuantity(form.getQuantity());

        gold.setCategory(category);


        if (form.getImage() != null && !form.getImage().isEmpty()) {


            gold.setImage(fileStorageService.storeFile(form.getImage()));

        }


        Gold saved = goldRepository.save(gold);


        return convertToDTO(saved);

    }


    @Override
    @Transactional
    public GoldDTO update(Integer id, GoldUpdateForm form) {


        Gold gold = goldRepository.findById(id).orElseThrow(() -> new RuntimeException("Gold not found"));


        Category category = categoryRepository.findById(form.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));


        gold.setName(form.getName());

        gold.setType(form.getType());

        gold.setWeight(form.getWeight());

        gold.setPrice(form.getPrice());

        gold.setQuantity(form.getQuantity());

        gold.setCategory(category);


        if (form.getImage() != null && !form.getImage().isEmpty()) {


            gold.setImage(fileStorageService.storeFile(form.getImage()));

        }


        return convertToDTO(goldRepository.save(gold));

    }


    @Override
    @Transactional
    public void delete(Integer id) {


        if (orderDetailRepository.existsByGold_Id(id)) {


            throw new RuntimeException("Không thể xóa vàng đã tồn tại trong đơn hàng.");

        }


        Gold gold = goldRepository.findById(id).orElseThrow(() -> new RuntimeException("Gold not found!"));


        goldRepository.delete(gold);

    }


    private GoldDTO convertToDTO(Gold gold) {


        GoldDTO dto = modelMapper.map(gold, GoldDTO.class);


        if (gold.getCategory() != null) {


            dto.setCategoryId(gold.getCategory().getId());


            dto.setCategoryName(gold.getCategory().getName());

        }


        return dto;

    }


}