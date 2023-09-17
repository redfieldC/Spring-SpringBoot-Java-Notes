package com.example.demo.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.helpers.MyResponseWrapper;
import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeService;

@RestController//@Controller, @ResponseBody
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee){
		employeeService.addEmployee(employee);
		MyResponseWrapper wrapper = new MyResponseWrapper("Employee Added!!", null);
		return new ResponseEntity<>(wrapper, HttpStatus.CREATED);
	}
	
	
	@GetMapping("")
	public ResponseEntity<?> getAllEmployees(){
		Collection<Employee> empData  = employeeService.getAllEmployeesData();
		MyResponseWrapper wrapper = new MyResponseWrapper("Employee data", empData);
		return new ResponseEntity<>(wrapper,HttpStatus.OK);
	}
	
//	@DeleteMapping("")
//	public String deleteEmpById(@RequestParam("id") int id,@RequestParam("name") String n) {
//		employeeService.deleteEmployeeById(id);
//		System.out.println("NAME --------->" + n);
//		return "DELETED ONCE AND FOR ALL";
//	}
	public ResponseEntity<?> deleteEmpById(@RequestParam("id") int id) {
		employeeService.deleteEmployeeById(id);
		MyResponseWrapper wrapper = new MyResponseWrapper("Employee DELETED", null);
		return new ResponseEntity<>(wrapper,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmpById(@PathVariable int id) {
		Employee emp = employeeService.getEmpById(id);
		MyResponseWrapper wrapper = new MyResponseWrapper("Employee Data Received", null);
		return new ResponseEntity<>(wrapper,HttpStatus.OK);
	}
	
//	@GetMapping("/{x}")
//	public Employee getEmpById(@PathVariable("x") int id) {
//		return employeeService.getEmpById(id);
//	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEmp(@PathVariable int id , @RequestBody Employee employee) {
		employeeService.updateEmployee(employee);
		MyResponseWrapper wrapper = new MyResponseWrapper("Employee UPDATED", null);
		return new ResponseEntity<>(wrapper,HttpStatus.OK);
	}
	
	
}