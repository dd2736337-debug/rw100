package com.vti.gold.service;

import com.vti.gold.dto.GoldDTO;
import com.vti.gold.form.GoldCreateForm;
import com.vti.gold.form.GoldUpdateForm;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IGoldService {
    Page<GoldDTO> findAll(Pageable pageable);

    GoldDTO findById(Integer id);

    void create(GoldCreateForm form);

    void update(Integer id, GoldUpdateForm form);

    void delete(Integer id);

}
