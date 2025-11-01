package com.example.employee.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "^[A-Za-z]+$", message = "Name must contain only letters")
    private String name;
    private String email;

    @Pattern(regexp = "\\d+", message = "Phone number must contain only digits")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 digits")
    private String mobile;
    private String role;
    private String accountName;
    @ManyToOne(cascade = CascadeType.ALL)
    //for foreign key to establish relationship {cascadeType-> parent table changes affects to child table)}
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private ProjectEntity projectEntity;
}
