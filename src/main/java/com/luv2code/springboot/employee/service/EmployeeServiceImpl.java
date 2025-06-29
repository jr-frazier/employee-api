package com.luv2code.springboot.employee.service;

import com.luv2code.springboot.employee.dao.EmployeeRepository;
import com.luv2code.springboot.employee.entity.Employee;
import com.luv2code.springboot.employee.request.EmployeeRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(long id) {
      Optional<Employee> result =  employeeRepository.findById(id);

      Employee theEmployee = null;

      if (result.isPresent()) {
          return result.get();
      } else {

          throw new RuntimeException("Did not find employee id - " + id);
      }
    }

    @Transactional
    @Override
    public Employee save(EmployeeRequest employeeRequest) {
        Employee employee = convertToEmployee(0, employeeRequest);
        return employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public Employee update(long id, EmployeeRequest employeeRequest) {
        Employee employee = convertToEmployee(id, employeeRequest);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee convertToEmployee(long id, EmployeeRequest employeeRequest) {
        return new Employee(id, employeeRequest.getFirstName(), employeeRequest.getLastName(), employeeRequest.getEmail());
    }

    @Transactional
    @Override
    public void deleteById(long theId) {
        employeeRepository.deleteById(theId);
    }


}
