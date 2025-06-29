package com.luv2code.springboot.employee.dao;

import com.luv2code.springboot.employee.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();
    Employee findById(long id);
    Employee save(Employee theEmployee);
    void deleteById(long theId);
}
