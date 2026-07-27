package com.vti.gold.service.Impl;

import com.vti.gold.dto.GoldDTO;
import com.vti.gold.entity.Category;
import com.vti.gold.entity.Gold;
import com.vti.gold.form.GoldCreateForm;
import com.vti.gold.form.GoldUpdateForm;
import com.vti.gold.repository.CategoryRepository;
import com.vti.gold.repository.GoldRepository;
import com.vti.gold.service.IGoldService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class GoldServiceImpl implements IGoldService {

    @Autowired
    private GoldRepository goldRepository;


    @Autowired
    private CategoryRepository categoryRepository;


    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Page<GoldDTO> findAll(Pageable pageable) {
        Page<Gold> page = goldRepository.findAll(pageable);
        return page.map(gold -> {
            GoldDTO dto = modelMapper.map(gold, GoldDTO.class);

            if (gold.getCategory() != null) {
                dto.setCategoryId(gold.getCategory().getId());
                dto.setCategoryName(gold.getCategory().getName());
            }

            return dto;
        });
    }

    @Override
    public GoldDTO findById(Integer id) {
        Gold gold = goldRepository.findById(id).orElseThrow(() -> new RuntimeException("Gold not found!"));
        GoldDTO dto = modelMapper.map(gold, GoldDTO.class);
        if (gold.getCategory() != null) {
            dto.setCategoryId(gold.getCategory().getId());
            dto.setCategoryName(gold.getCategory().getName());
        }
        return dto;
    }

    @Override
    public void create(GoldCreateForm form) {
        Category category = categoryRepository.findById(form.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found!"));
        Gold gold = new Gold();

        gold.setName(form.getName());
        gold.setType(form.getType());
        gold.setWeight(form.getWeight());
        gold.setPrice(form.getPrice());
        gold.setQuantity(form.getQuantity());
        gold.setCategory(category);


        String image =
                saveImage(form.getImage());

        gold.setImage(image);


        goldRepository.save(gold);

    }

    @Override
    public void update(Integer id, GoldUpdateForm form) {
        Gold gold = goldRepository.findById(id).orElseThrow(() -> new RuntimeException("Gold not found!"));


        Category category = categoryRepository.findById(form.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found!"));


        gold.setName(form.getName());
        gold.setType(form.getType());
        gold.setWeight(form.getWeight());
        gold.setPrice(form.getPrice());
        gold.setQuantity(form.getQuantity());
        gold.setCategory(category);
        if (form.getImage() != null &&
                !form.getImage().isEmpty()) {

            String image =
                    saveImage(form.getImage());

            gold.setImage(image);
        }
        goldRepository.save(gold);

    }

    @Override
    public void delete(Integer id) {

        Gold gold = goldRepository.findById(id).orElseThrow(() -> new RuntimeException("Gold not found!"));

        goldRepository.delete(gold);
    }

    //Tạo file lưu ảnh và check trung ảnh

    private final String UPLOAD_DIR =
            "src/main/resources/static/images/";

    private String saveImage(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return null;
        }
        if (!isImage(file)){
            throw new RuntimeException(
                    "File phải là ảnh jpg, jpeg hoặc png"
            );
        }
        try {

            File folder = new File(UPLOAD_DIR);

            if (!folder.exists()) {
                folder.mkdirs();
            }


            String originalName =
                    StringUtils.cleanPath(
                            file.getOriginalFilename()
                    );


            String fileName =
                    UUID.randomUUID()
                            + "_"
                            + originalName;


            Path path =
                    Paths.get(
                            UPLOAD_DIR + fileName
                    );


            Files.write(
                    path,
                    file.getBytes()
            );


            return "/images/" + fileName;


        } catch (IOException e) {

            throw new RuntimeException(
                    "Không thể lưu ảnh"
            );
        }
    }
    private boolean isImage(MultipartFile file){

        String contentType = file.getContentType();

        return contentType != null &&
                (contentType.equals("image/jpeg")
                        || contentType.equals("image/png")
                        || contentType.equals("image/jpg"));
    }
}
