package com.security.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.model.Employee;

@RestController
public class EmployeeController {

   /* @RequestMapping(value = "/employee", produces = "application/json")
    @ResponseBody
    public String employee() {
    	
    	return "Welcome user";
    }
    */
    @RequestMapping(value = "/getEmployeesList", produces = "application/json")
    @ResponseBody
    public List<Employee> getEmployeesList() {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        
        employee.setId("1");
        employee.setName("san");
        employees.add(employee);
        return employees;

    }
  
}
