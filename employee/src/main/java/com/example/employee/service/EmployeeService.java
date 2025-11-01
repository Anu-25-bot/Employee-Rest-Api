package com.example.employee.service;


import com.example.employee.model.EmployeeEntity;
import com.example.employee.model.ProjectEntity;

import java.util.List;

public interface EmployeeService {
    EmployeeEntity save(EmployeeEntity employeeEntity);

    List<EmployeeEntity> saveAll(List<EmployeeEntity> employeeEntityList);

    EmployeeEntity findById(Long id);

    EmployeeEntity findByName(String name);

    EmployeeEntity findByMobile(String mobile);

    List<EmployeeEntity> findAllEmployee();

    List<EmployeeEntity> findids(List<Long> ids);

    EmployeeEntity updateEmployee(Long id, EmployeeEntity employeeEntity);

    EmployeeEntity deleteById(Long id);

    List<EmployeeEntity> fetchByorder();

    List<EmployeeEntity> firstTenEmployee();

    List<EmployeeEntity> getAllEmployees();

    ProjectEntity deleteProject(Long id);

    List<EmployeeEntity> findWithStartingLetter(String start, String end);

    List<EmployeeEntity> getPaginatedEmployee(int page, int pageSize);

}
