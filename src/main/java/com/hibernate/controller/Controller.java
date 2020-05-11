package com.hibernate.controller;

import com.hibernate.dao.EmployeeRepository;
import com.hibernate.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class Controller {

    @Autowired
    private EmployeeRepository repository;

    @PostMapping("/employee")
    public ResponseEntity addEmployee(@RequestBody @Valid Employee employee){
        repository.save(employee);
        return new ResponseEntity(HttpStatus.OK);
    }
}
