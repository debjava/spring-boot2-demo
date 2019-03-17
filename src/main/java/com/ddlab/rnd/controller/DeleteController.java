package com.ddlab.rnd.controller;

import com.ddlab.rnd.entities.Employee;
import com.ddlab.rnd.service.EmpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(
    value = "Delete Employe Info in an Organization",
    description = "API for all Employees",
    tags = {"Employee deletion"})
@RequestMapping(value = "/delete")
@RestController
public class DeleteController {

  @Autowired private EmpService empService;

  @ApiOperation(
      value = "Create an employee in organization",
      tags = {"Employee deletion"})
  @ApiResponses(
      value = {
        @ApiResponse(code = 204, response = Void.class, message = "Deleted"),
        @ApiResponse(code = 500, message = "Unable to create employees")
      })
  @DeleteMapping("emp/{id}")
  public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer id) {
    empService.deleteEmployee(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }
}
