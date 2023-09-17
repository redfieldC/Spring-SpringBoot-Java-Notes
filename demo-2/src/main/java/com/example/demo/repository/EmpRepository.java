package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Emp;

public interface EmpRepository extends CrudRepository<Emp, Integer> {
	public Optional<Emp> findByEmail(String email);
}
