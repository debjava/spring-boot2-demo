package com.ddlab.rnd.controller;

import com.ddlab.rnd.entities.Employee;
import com.ddlab.rnd.service.EmpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(
    value = "All Employees in an Organization",
    description = "API for all Employees",
    tags = {"All Employees"})
@RequestMapping(value = "/get")
@RestController
public class GetController {

  @Autowired private EmpService empService;

  @ApiOperation(
      value = "Provides list of Employees in an organization",
      tags = {"All Employees"})
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, response = Employee[].class, message = "Retrieved"),
        @ApiResponse(code = 500, message = "Unable to retrieve employees")
      })
  @GetMapping(path = "/allEmps")
  public ResponseEntity<?> getAllEmployees() {
    List<Employee> allEmps = empService.getAllEmployees();
    return ResponseEntity.ok(allEmps);
  }

  @ApiOperation(
      value = "Provides list of Employees with NullPointer Exception",
      tags = {"All Employees"})
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, response = Employee[].class, message = "Received"),
        @ApiResponse(code = 500, message = "Unable to retrieve employees")
      })
  @GetMapping(path = "/allEmpException1")
  public ResponseEntity<?> getAllEmpsWithException1() {
    List<Employee> allEmps = empService.getAllEmpsException1();
    return ResponseEntity.ok(allEmps);
  }

  @ApiOperation(
      value = "Provides Employee by Id",
      tags = {"All Employees"})
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, response = Employee.class, message = "Received"),
        @ApiResponse(code = 400, response = String.class, message = "Bad request to get emp info"),
        @ApiResponse(code = 500, message = "Unable to retrieve employees")
      })
  @GetMapping(
      path = "/emp/id/{id}",
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<?> getEmployeePerId(
      @PathVariable(value = "id", required = true) String id) {
    Employee empById = empService.getEmployeeById(id);
    return ResponseEntity.ok(empById);
  }

  @ApiOperation(
      value = "Search Employee by name",
      tags = {"All Employees"})
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, response = Employee.class, message = "Received"),
        @ApiResponse(code = 400, response = String.class, message = "Bad request to get emp info"),
        @ApiResponse(code = 500, message = "Unable to retrieve employees")
      })
  @GetMapping(
      path = "/emp/name",
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<?> getEmployeeByName(
      @RequestParam(value = "firstName", required = false) String firstName,
      @RequestParam(value = "lastName", required = false) String lastName) {
    Employee empById = empService.searchEmpByName(firstName, lastName);
    return ResponseEntity.ok(empById);
  }
}
