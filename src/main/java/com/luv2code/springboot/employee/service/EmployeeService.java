package com.luv2code.springboot.employee.service;

import com.luv2code.springboot.employee.entity.Employee;
import com.luv2code.springboot.employee.request.EmployeeRequest;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(long id);
    Employee save(EmployeeRequest employeeRequest);
    Employee update(long id, EmployeeRequest employeeRequest);
    Employee convertToEmployee(long id, EmployeeRequest employeeRequest);
    void deleteById(long theId);
}
