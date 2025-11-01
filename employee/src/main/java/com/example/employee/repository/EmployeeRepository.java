package com.example.employee.repository;

import com.example.employee.model.EmployeeEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    boolean existsBymobile(String mobile);

    EmployeeEntity findByName(String name);

    EmployeeEntity findBymobile(String mobile);

    @Query("SELECT e FROM EmployeeEntity e")
    List<EmployeeEntity> fetchAllEmployees();


@Query(value = "SELECT * FROM employee_entity WHERE UPPER(LEFT(name, 1)) BETWEEN ?1 AND ?2", nativeQuery = true)
        //(endpoint?start=A&end=B)
//    @Query(value = "SELECT * FROM employee_entity WHERE UPPER(LEFT(name, 1)) BETWEEN :start AND :end", nativeQuery = true)
    List<EmployeeEntity> findByNameStartingBetween( String start,String end);
 Page<EmployeeEntity> findAll(Pageable pageable);
}
