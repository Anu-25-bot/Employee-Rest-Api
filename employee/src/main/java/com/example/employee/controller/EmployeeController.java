package com.example.employee.controller;

import com.example.employee.model.EmployeeEntity;

import com.example.employee.model.ProjectEntity;
import com.example.employee.service.EmployeeService;
import com.example.employee.service.ServiceImplementation;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/employee")

public class EmployeeController {
    @Autowired
    EmployeeService service;

    @PostMapping("/save")
    public ResponseEntity<EmployeeEntity> saveEmployee(@Valid @RequestBody EmployeeEntity employeeEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(employeeEntity));
    }

    @PostMapping("/saveAll")
    public ResponseEntity<List<EmployeeEntity>> saveAll(@Valid @RequestBody List<EmployeeEntity> employeesEntity) {
        return ResponseEntity.status(HttpStatus.OK).body(service.saveAll(employeesEntity));
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeEntity> findByid(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<EmployeeEntity>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllEmployee());
    }

    @GetMapping("/findByName")
    public ResponseEntity<EmployeeEntity> findbyname(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByName(name));
    }

    @GetMapping("/findByMobile")
    public ResponseEntity<EmployeeEntity> findbyMobile(@RequestParam String mobile) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByMobile(mobile));
    }

    @GetMapping("/ids")
    public ResponseEntity<List<EmployeeEntity>> findByIds(@RequestParam List<Long> ids) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findids(ids));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployee(@Valid @PathVariable Long id, @RequestBody EmployeeEntity employeeEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.updateEmployee(id, employeeEntity));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EmployeeEntity> deleteEmployee(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.deleteById(id));
    }

    @GetMapping("/findByAlphabeticalOrder")
    public ResponseEntity<List<EmployeeEntity>> findByOrder() {
        return ResponseEntity.status(HttpStatus.OK).body(service.fetchByorder());
    }

    @GetMapping("/top10")
    public ResponseEntity<List<EmployeeEntity>> getTop10Employees() {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.firstTenEmployee());
    }

    @GetMapping("/AllEmployees")
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
        return  ResponseEntity.status(HttpStatus.OK).body(service.getAllEmployees());

    }
    @DeleteMapping("/deleteProject/{id}")
    public ResponseEntity<ProjectEntity> deleteProjectID(@PathVariable Long id){
        return  ResponseEntity.status(HttpStatus.OK).body(service.deleteProject(id));

    }
    @GetMapping("/pagination")
    public ResponseEntity<List<EmployeeEntity>> findByLetter(@RequestParam int page,@RequestParam int size) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getPaginatedEmployee(page,size));

    }
    @GetMapping("/by-name-range")
    public List<EmployeeEntity> getEmployeesByNameRange(
            @RequestParam String start,
            @RequestParam String end) {
        return service.findWithStartingLetter(start,end);
    }

}

