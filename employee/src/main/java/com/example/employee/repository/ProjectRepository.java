package com.example.employee.repository;

import com.example.employee.model.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectEntity,Long> {

}
