package com.example.library.repository;

import com.example.library.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Boolean existsEmployeeByLogin(String login);
    Employee findEmployeeByLogin(String login);
}
