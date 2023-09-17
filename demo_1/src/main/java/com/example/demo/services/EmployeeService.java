package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private	EmployeeRepository employeeRepository;
	//adding the object in map - put(key, value)
	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	
	public Iterable<Employee> getAllEmployeesData() {
		return employeeRepository.findAll();
	}
	
	public Employee getEmpById(int id) {
		return employeeRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"The EMPLOYEE IS NOT FOUND"));
	}
	
//	public void updateEmployee(Employee employee) {
//		getEmpById(employee.getId());
//		allEmployees.put(employee.getId(), employee);
//	}
	
	public void deleteEmployeeById(int id) {
		employeeRepository.deleteById(id);
	}
}
