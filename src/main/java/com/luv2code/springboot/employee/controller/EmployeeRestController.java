package com.luv2code.springboot.employee.controller;

import com.luv2code.springboot.employee.entity.Employee;
import com.luv2code.springboot.employee.request.EmployeeRequest;
import com.luv2code.springboot.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name="Employee Rest API Endpoints", description="Employee REST API Endpoints")
public class EmployeeRestController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Get all employees", description = "Get a list of all employees")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @Operation(summary = "Get employee by id", description = "Get a single employee by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public Employee findById(@PathVariable @Min(value = 1 ) long id) {
        return employeeService.findById(id);
    }

    @Operation(summary = "Add new employee", description = "Add a new employee")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Employee addEmployee(@Valid @RequestBody EmployeeRequest employee) {
        return employeeService.save(employee);

    }

    @Operation(summary = "Update employee info", description = "Update an employee's information")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}")
    public Employee updateEmployee(@PathVariable @Min(value = 1) long id, @Valid @RequestBody EmployeeRequest employee) {
        return employeeService.update(id, employee);
    }

    @Operation(summary = "Delete employee", description = "Delete an employee")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable @Min(value = 1) long id) {
        employeeService.deleteById(id);
    }

}
