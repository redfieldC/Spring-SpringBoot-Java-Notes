package com.example.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.helpers.MyResponseWrapper;
import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeService;

import jakarta.validation.Valid;

@RestController//@Controller, @ResponseBody
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("")
	public ResponseEntity<?> addEmployee(@RequestBody @Valid Employee employee, BindingResult bindingResult){
		if(bindingResult.hasFieldErrors()) {
			int count=bindingResult.getErrorCount();
			List<String> allErrors= bindingResult.getFieldError()
					.stream()
					.map(error->error.getField() +": "+error.getDefalutMessage())
					.collect(Collectors.toList());
			String error = String.join(",", allErrors);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors);
			
		}
		employeeService.addEmployee(employee);
		MyResponseWrapper wrapper = new MyResponseWrapper("Employee Added!!", null);
		return new ResponseEntity<>(wrapper, HttpStatus.CREATED);
	}
	
	
	@GetMapping("")
	public ResponseEntity<?> getAllEmployees(){
		Iterable<Employee> empData  = employeeService.getAllEmployeesData();
		MyResponseWrapper wrapper = new MyResponseWrapper("Employee data", empData);
		return new ResponseEntity<>(wrapper,HttpStatus.OK);
	}
//	

//	public ResponseEntity<?> deleteEmpById(@RequestParam("id") int id) {
//		employeeService.deleteEmployeeById(id);
//		MyResponseWrapper wrapper = new MyResponseWrapper("Employee DELETED", null);
//		return new ResponseEntity<>(wrapper,HttpStatus.OK);
//	}
//	@GetMapping("/{id}")
//	public ResponseEntity<?> getEmpById(@PathVariable int id) {
//		Employee emp = employeeService.getEmpById(id);
//		MyResponseWrapper wrapper = new MyResponseWrapper("Employee Data Received", null);
//		return new ResponseEntity<>(wrapper,HttpStatus.OK);
//	}
	

	
//	@PutMapping("/{id}")
//	public ResponseEntity<?> updateEmp(@PathVariable int id , @RequestBody Employee employee) {
//		employeeService.updateEmployee(employee);
//		MyResponseWrapper wrapper = new MyResponseWrapper("Employee UPDATED", null);
//		return new ResponseEntity<>(wrapper,HttpStatus.OK);
//	}
}
