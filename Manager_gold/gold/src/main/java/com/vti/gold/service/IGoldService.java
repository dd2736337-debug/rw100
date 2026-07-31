package com.vti.gold.service;

import com.vti.gold.dto.GoldDTO;
import com.vti.gold.form.GoldCreateForm;
import com.vti.gold.form.GoldUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IGoldService {
    Page<GoldDTO> findAll( String name,
                           String type, Pageable pageable);

    GoldDTO findById(Integer id);

    GoldDTO create(GoldCreateForm form);

    GoldDTO update(Integer id, GoldUpdateForm form);

    void delete(Integer id);

}
