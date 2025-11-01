package com.example.employee.service;

import com.example.employee.exception.AlreadyFoundException;
import com.example.employee.exception.NotFoundException;
import com.example.employee.model.EmployeeEntity;
import com.example.employee.model.ProjectEntity;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceImplementation implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ProjectRepository projectRepository;


    @Override
    public EmployeeEntity save(EmployeeEntity employeeEntity) {
        if (!employeeRepository.existsBymobile(employeeEntity.getMobile())) {
            return employeeRepository.save(employeeEntity);
        } else {
            throw new AlreadyFoundException("Mobile number exists");
        }

    }

    @Override
    public List<EmployeeEntity> saveAll(List<EmployeeEntity> employeeEntityList) {
        ArrayList<EmployeeEntity> employeeEntities = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeEntities) {
            if (!employeeRepository.existsBymobile(employeeEntity.getMobile())) {
                employeeRepository.save(employeeEntity);
            } else {
                throw new AlreadyFoundException("Mobile number exists");
            }
        }
        return employeeRepository.saveAll(employeeEntityList);
    }

    @Override
    public EmployeeEntity findById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        if (employeeEntity == null) {
            throw new NotFoundException("id not found");
        }

        return employeeEntity;
    }

    @Override
    public EmployeeEntity findByName(String name) {
        EmployeeEntity employeeEntity = employeeRepository.findByName(name);
        if (employeeEntity == null) {
            throw new NotFoundException("id not found");
        }
        return employeeEntity;
    }

    @Override
    public EmployeeEntity findByMobile(String mobile) {
        EmployeeEntity employeeEntity = employeeRepository.findBymobile(mobile);
        if (employeeEntity == null) {
            throw new NotFoundException("id not found");
        }
        return employeeEntity;
    }


    @Override
    public List<EmployeeEntity> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public List<EmployeeEntity> findids(List<Long> ids) {
        return employeeRepository.findAllById(ids);
    }

    @Override
    public EmployeeEntity updateEmployee(Long id, EmployeeEntity employeeEntity) {
        EmployeeEntity employeeEntity1 = employeeRepository.findById(id).orElse(null);
        if (employeeEntity == null) {
            throw new NotFoundException("id not found");
        }
        employeeEntity1.setEmail(employeeEntity.getEmail());
        employeeEntity1.setMobile(employeeEntity.getMobile());
        return employeeRepository.save(employeeEntity1);
    }

    @Override
    public EmployeeEntity deleteById(Long id) {
        EmployeeEntity employeeEntity2 = employeeRepository.findById(id).orElse(null);
        if (employeeEntity2 == null) {
            throw new NotFoundException("id not found");
        }
        employeeRepository.deleteById(id);
        return employeeEntity2;
    }

    @Override
    public List<EmployeeEntity> fetchByorder() {
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public List<EmployeeEntity> firstTenEmployee() {
        //return top 10 employees
        return employeeRepository.findAll().stream().limit(10).collect(Collectors.toList());
        //return employees name>4
        // return employeeRepository.findAll().stream().filter(employeeEntity -> employeeEntity.getName().length()>4).collect(Collectors.toList());

    }

    @Override
    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.fetchAllEmployees();
    }

    @Override
    public ProjectEntity deleteProject(Long id) {
        ProjectEntity projectEntity = projectRepository.findById(id).orElse(null);
        if (projectEntity == null) {
            throw new NotFoundException("id not found");
        }
        projectRepository.deleteById(projectEntity.getId());
        return projectEntity;
    }

    @Override
    public List<EmployeeEntity> findWithStartingLetter(String start, String end) {

        return employeeRepository.findByNameStartingBetween(start.toUpperCase(), end.toUpperCase());
    }


    @Override
    public List<EmployeeEntity> getPaginatedEmployee(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, "name"));
        return employeeRepository.findAll(pageable).getContent();
    }

//    @Override
//    public List<EmployeeEntity> findWithStartingLetter(String startingLetter,String EndingLetter) {
//        return employeeRepository.findEmployeesByLetterRange(startingLetter, EndingLetter);
//    }


}



