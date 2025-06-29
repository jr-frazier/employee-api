package com.luv2code.springboot.employee.dao;

import com.luv2code.springboot.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
