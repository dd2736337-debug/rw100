package com.vti.testtingsystem.service.impl;

import com.vti.testtingsystem.dto.AccountDTO;
import com.vti.testtingsystem.entity.Account;
import com.vti.testtingsystem.entity.Department;
import com.vti.testtingsystem.entity.Position;
import com.vti.testtingsystem.form.AccountCreateAndUpdateForm;
import com.vti.testtingsystem.repository.IAccountRepository;
import com.vti.testtingsystem.repository.IDepartmentRepository;
import com.vti.testtingsystem.repository.IPositionRepository;
import com.vti.testtingsystem.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountRepository repository;
    @Autowired
    private IDepartmentRepository departmentRepository;

    @Autowired
    private IPositionRepository positionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AccountDTO> findAll() {
//        List<Account> accounts= repository.findAll();
//        //chuyển list account thanhd list accountDTO
//        List<AccountDTO> accountDTOS =new ArrayList<>();
//        for (Account account:accounts){
//            AccountDTO dto=modelMapper.map(account, AccountDTO.class);
//            accountDTOS.add(dto);
//        }
//        List<Account> accounts = repository.findAll();
//        List<AccountDTO> dto = accounts.stream().map(a -> modelMapper.map(a, AccountDTO.class)).toList();
//        return dto;
        return repository.findAll().stream().map(acc -> modelMapper.map(acc, AccountDTO.class)).toList();
    }

    @Override
    public AccountDTO findById(Integer id) {
        Account account = repository.findById(id).orElseThrow(() -> new RuntimeException("Account không tồn tại"));
        AccountDTO dto = modelMapper.map(account, AccountDTO.class);
        if (account.getDepartment() != null) {
            dto.setDepartmentId(account.getDepartment().getId());
            dto.setDepartmentName(account.getDepartment().getName());
        }
        if (account.getPosition() != null) {
            dto.setPositionName(account.getPosition().getName().name());
            dto.setPositionId(account.getPosition().getId());
        }
        return dto;
    }

    @Override
    public AccountDTO findByFullName(String fullName) {
        Account account = repository.findByFullName(fullName).orElseThrow(() -> new RuntimeException("Account không tồn tại"));

        AccountDTO dto = modelMapper.map(account, AccountDTO.class);
        if (account.getDepartment() != null) {
            dto.setDepartmentName(account.getDepartment().getName());
        }
        if (account.getPosition() != null) {
            dto.setPositionName(account.getPosition().getName().name());
        }
        return dto;
    }

    @Override
    public AccountDTO findByEmail(String email) {
        Account account = repository.findByEmail(email).orElseThrow(() -> new RuntimeException("Account không tồn tại"));
        AccountDTO dto = modelMapper.map(account, AccountDTO.class);
        if (account.getDepartment() != null) {
            dto.setDepartmentId(account.getDepartment().getId());
            dto.setDepartmentName(account.getDepartment().getName());
        }
        if (account.getPosition() != null) {
            dto.setPositionName(account.getPosition().getName().name());
            dto.setPositionId(account.getPosition().getId());
        }
        return dto;
    }

    @Override
    public void create(AccountCreateAndUpdateForm form) {
        if (repository.existsByUserNameAndIdNot(form.getUserName(), null)) {
            throw new RuntimeException("User Đã tồn tại");
        }
        if (repository.existsByEmailAndIdNot(form.getEmail(), null)) {
            throw new RuntimeException("Email Đã tồn tại");
        }
        Department department = departmentRepository.findById(form.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department không tồn tại"));
        Position position = positionRepository.findById(form.getPositionId())
                .orElseThrow(() -> new RuntimeException("Position không tồn tại"));
        Account account = new Account();
        account.setUserName(form.getUserName());
        account.setFullName(form.getFullName());
        account.setEmail(form.getEmail());
        account.setDepartment(department);
        account.setPosition(position);
        repository.save(account);
    }

    @Override
    public void update(AccountCreateAndUpdateForm form, Integer id) {
        Account account = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account không tồn tại"));
        if (repository.existsByUserNameAndIdNot(form.getUserName(), id)) {
            throw new RuntimeException("User Đã tồn tại");
        }
        if (repository.existsByEmailAndIdNot(form.getEmail(),id)) {
            throw new RuntimeException("Email Đã tồn tại");
        }
        Department department = departmentRepository.findById(form.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department không tồn tại"));
        Position position = positionRepository.findById(form.getPositionId())
                .orElseThrow(() -> new RuntimeException("Position không tồn tại"));
        account.setUserName(form.getUserName());
        account.setFullName(form.getFullName());
        account.setEmail(form.getEmail());
        account.setDepartment(department);
        account.setPosition(position);
        repository.save(account);
    }

    @Override
    public void delete(Integer id) {
        Account account = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account không tồn tại"));
        repository.deleteById(id);

    }


//    @Override
//    public void create(AccountDto accountDto) {
//        Department department = departmentRepository.findById(accountDto.getDepartmentId())
//                .orElseThrow(() -> new RuntimeException("Department không tồn tại"));
//        // tim póition
//        Position position = positionRepository.findById(accountDto.getPositionId()).orElseThrow(() -> new RuntimeException("Position không tồn tại"));
//
//        Account account = new Account();
//        account.setUserName(accountDto.getUserName());
//        account.setFullName(accountDto.getFullName());
//        account.setEmail(accountDto.getEmail());
//        account.setDepartment(department);
//        account.setPosition(position);
//
//        repository.save(account);
//    }
//
//    @Override
//    public void update(AccountDto accountDto, Integer id) {
//        Account account = repository.findById(id).orElseThrow(() -> new RuntimeException("Account không tồn tại"));
//        Department department = departmentRepository.findById(accountDto.getDepartmentId()).orElseThrow(() -> new RuntimeException("Department không tồn tại"));
//        // tim póition
//        Position position = positionRepository.findById(accountDto.getPositionId()).orElseThrow(() -> new RuntimeException("Position không tồn tại"));
//        account.setUserName(accountDto.getUserName());
//        account.setFullName(accountDto.getFullName());
//        account.setEmail(accountDto.getEmail());
//        account.setDepartment(department);
//        account.setPosition(position);// model mapper
//        repository.save(account);
//
//    }
//

}
