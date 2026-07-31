package com.vti.gold.controller;

import com.vti.gold.dto.GoldDTO;
import com.vti.gold.form.GoldCreateForm;
import com.vti.gold.form.GoldUpdateForm;
import com.vti.gold.service.IGoldService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/golds")
@CrossOrigin("*")
public class GoldController {
    @Autowired
    private IGoldService goldService;


    // Lấy danh sách vàng có phân trang
    @GetMapping
    public Page<GoldDTO> findAll(@RequestParam(required = false) String name,

                                 @RequestParam(required = false) String type,
                                 @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return goldService.findAll(name, type, pageable);
    }

    @GetMapping("/{id}")
    public GoldDTO findById(@PathVariable Integer id) {

        return goldService.findById(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GoldDTO create(@Valid @ModelAttribute GoldCreateForm form) {

        return goldService.create(form);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GoldDTO update(
            @PathVariable Integer id,
            @Valid @ModelAttribute GoldUpdateForm form
    ) {

        return goldService.update(id, form);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Integer id
    ) {

        goldService.delete(id);
    }

}
