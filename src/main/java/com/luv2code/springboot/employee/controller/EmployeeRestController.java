package com.luv2code.springboot.employee.controller;

import com.luv2code.springboot.employee.dao.EmployeeDAO;
import com.luv2code.springboot.employee.entity.Employee;
import com.luv2code.springboot.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

   @GetMapping("{id}")
    public Employee findById(@PathVariable long id) {
        return employeeService.findById(id);
    }

}
