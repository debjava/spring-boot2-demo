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

@Api(
    value = "Update Employee Partially in Organization",
    description = "API for employee updation partially in organisation",
    tags = {"Employee Patch updation"})
@RequestMapping(value = "/patch")
@RestController
public class PatchController {

  @Autowired private EmpService empService;

  @ApiOperation(
      value = "Create an employee in organization",
      tags = {"Employee Patch updation"})
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, response = Employee.class, message = "Ok"),
        @ApiResponse(code = 500, message = "Unable to update employees")
      })
  @PatchMapping(
      path = "/updatePartialEmp",
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<?> createEmployee(
      @RequestParam(value = "id", required = true) String id,
      @RequestParam(value = "loginName", required = true) String loginName) {
    // First find the emp by id
    Employee empById = empService.getEmployeeById(id);
    empById.setFirstName(loginName);
    Employee updatedEmp = empService.updateEmployeeLogin(empById);
    System.out.println("updatedEmp = " + updatedEmp);
    return ResponseEntity.ok(updatedEmp);
  }
}
