package com.vti.testtingsystem.service.impl;

import com.vti.testtingsystem.Enum.PositionName;
import com.vti.testtingsystem.dto.PositionDto;
import com.vti.testtingsystem.entity.Position;
import com.vti.testtingsystem.repository.IAccountRepository;
import com.vti.testtingsystem.repository.IPositionRepository;
import com.vti.testtingsystem.service.IPositionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements IPositionService {
    @Autowired
    private IPositionRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public List<PositionDto> findAll() {
        return repository.findAll().stream().map(position -> modelMapper.map(position, PositionDto.class)).toList();
    }

    @Override
    public PositionDto findById(Integer id) {
        Position position=repository.findById(id).orElseThrow(()->new RuntimeException("Position không tồn tại"));
        return modelMapper.map(position, PositionDto.class);

    }

    @Override
    public PositionDto findByName(PositionName name) {
        Position position=repository.findByName(name).orElseThrow(()->new RuntimeException("Position không tồn tại"));
        return modelMapper.map(position, PositionDto.class);
    }

//    @Override
//    public void create(PositionDto dto) {
//        Position position = new Position();
//        position.setName(dto.getPositionName());
//        repository.save(position);
//    }
//
//    @Override
//    public void update(PositionDto dto, Integer id) {
//        Position position = repository.findById(id).orElseThrow(() -> new RuntimeException("Position không tồn tại"));
//        if (position != null) {
//            position.setName(dto.getPositionName());
//            repository.save(position);
//        }
//
//    }

//    @Override
//    public void delete(Integer id) {
//        //Xóa acc có position_id=id liên quan
//
//        repository.deleteById(id);
//
//    }

}
