package com.ddlab.rnd.controller;

import com.ddlab.rnd.entities.Employee;
import com.ddlab.rnd.service.EmpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Api(
    value = "Update Employee in Organization",
    description = "API for employee updation in organisation",
    tags = {"Employee updation"})
@RequestMapping(value = "/put")
@RestController
public class PutController {

  @Autowired private EmpService empService;

  @ApiOperation(
      value = "Create an employee in organization",
      tags = {"Employee updation"})
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, response = Employee.class, message = "Ok"),
        @ApiResponse(code = 500, message = "Unable to create employees")
      })
  @PutMapping(
      path = "/updateEmp",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<?> createEmployee(@RequestBody(required = true) Employee emp) {
    Employee updatedEmp = empService.updateEmployeeInfo(emp);
    return ResponseEntity.ok(updatedEmp);
  }
}
