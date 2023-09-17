package com.example.demo.services;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Employee;

@Service
public class EmployeeService {

	private Map<Integer,Employee> allEmployees = new HashMap<>();
	
	//adding the object in map - put(key, value)
	public void addEmployee(Employee employee) {
		allEmployees.put(employee.getId(), employee);
	}
	
	public Collection<Employee> getAllEmployeesData() {
		return allEmployees.values();
	}
	
	public Employee getEmpById(int id) {
		Employee emp  = allEmployees.get(id);
		if (emp != null)
			return emp;
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The employee with id DOESNT EXIST");
	}
	
	public void updateEmployee(Employee employee) {
		getEmpById(employee.getId());
		allEmployees.put(employee.getId(), employee);
	}
	
	public void deleteEmployeeById(int id) {
		allEmployees.remove(id);
	}
	
	
	
}