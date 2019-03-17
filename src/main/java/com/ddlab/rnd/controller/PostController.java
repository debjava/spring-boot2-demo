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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Api(
    value = "Add Employee in Organization",
    description = "API for add employee in organisation",
    tags = {"Employee creation"})
@RequestMapping(value = "/post")
@RestController
public class PostController {

  @Autowired private EmpService empService;

  @ApiOperation(
      value = "Create an employee in organization",
      tags = {"Employee creation"})
  @ApiResponses(
      value = {
        @ApiResponse(code = 201, response = Void.class, message = "Created"),
        @ApiResponse(code = 500, message = "Unable to create employees")
      })
  @PostMapping(
      path = "/createEmp",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Void> createEmployee(@RequestBody(required = true) Employee emp) {
    empService.createEmployee(emp);
    //    URI location =
    //        ServletUriComponentsBuilder.fromCurrentRequest()
    //            .path("/{id}")
    //            .buildAndExpand(emp.getEmpId())
    //            .toUri();
    //    return ResponseEntity.created(location).build();
    UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/ddlab/get");
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(builder.path("/emp/id/{id}").buildAndExpand(emp.getEmpId()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @ApiOperation(
      value = "Saves employee information in database",
      tags = {"Employee creation"})
  @ApiResponses(
      value = {
        @ApiResponse(code = 201, response = Void.class, message = "Created"),
        @ApiResponse(code = 500, message = "Unable to create employees")
      })
  @PostMapping(
      path = "/saveEmp",
      consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
  public ResponseEntity<Void> saveEmployee(
      @RequestParam(value = "id", required = false) int id,
      @RequestParam(value = "firstName", required = true) String firstName,
      @RequestParam(value = "lastName", required = true) String lastName) {
    Employee emp = new Employee(id, firstName, lastName);
    // It works like @FormParam in Jersey
    System.out.println("emp = " + emp);
    empService.createEmployee(emp);
    UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/ddlab/get");
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(builder.path("/emp/id/{id}").buildAndExpand(emp.getEmpId()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @ApiOperation(
      value = "Saves all employees information in database with an admin key",
      tags = {"Employee creation"})
  @ApiResponses(
      value = {
        @ApiResponse(code = 201, response = Void.class, message = "Created"),
        @ApiResponse(code = 500, message = "Unable to create employees")
      })
  @PostMapping(
      path = "/createEmpKey",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Void> createEmployeeKey(
      @RequestHeader("key") String Key, @RequestBody(required = true) Employee emp) {
    System.out.println("Key = " + Key);
    UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/ddlab/get");
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(builder.path("/emp/id/{id}").buildAndExpand(111).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }
}
