package com.ddlab.rnd.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

public class MatrixController {

    @ApiOperation(
            value = "Check employee details",
            tags = {"All Employees"})
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, response = String.class, message = "Received"),
                    @ApiResponse(code = 400, response = String.class, message = "Bad request to get emp info"),
                    @ApiResponse(code = 500, message = "Unable to retrieve employees")
            })
    @GetMapping(path = "/employee/check")
    public ResponseEntity<?> handler(@MatrixVariable("mat") Map<String, String> matrixVars) {
        System.out.println("Hitting the URL /employee/check");
        System.out.println("matrixVars = " + matrixVars);
        //    return new ResponseEntity<>(matrixVars, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    //  public ResponseEntity<?> handler(
    //      @MatrixVariable("id") int id,
    //      @MatrixVariable("firstName") String firstName,
    //      @MatrixVariable("lastName") String lastName) {
    //    System.out.println("ID = " + id);
    //    System.out.println("First Name = " + firstName);
    //    System.out.println("Last Name = " + lastName);
    //    return ResponseEntity.ok("Received all information");
    //  }

    @GetMapping("employeeData/{employee}")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getEmployeeData(
            @MatrixVariable Map<String, String> matrixVars) {
        System.out.println("matrixVars = " + matrixVars);
        return new ResponseEntity<>(matrixVars, HttpStatus.OK);
    }










    @GetMapping(value = "/user/{first}/{last}",
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String handler1(@MatrixVariable("first") String first,
                           @MatrixVariable("last") String last) {

        return String.format("Hello %s %s", first, last);
    }

    @GetMapping(value = "/data/{user:.*}",
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String handler2(@MatrixVariable Map<String, String> data) {

        System.out.println("data = " + data);
        return String.format("Id: %s\nFirst name: %s\nLast Name: %s\nEmail: %s\n",
                data.get("id"), data.get("first"), data.get("last"), data.get("email"));
    }

    @GetMapping(value = "/geo/{continent}",
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String handler3(@PathVariable("continent") String continent,
                           @MatrixVariable("country") String country,
                           @MatrixVariable("capital") String capital) {
        System.out.println("continent = " + continent);
        System.out.println("country = " + country);
        System.out.println("capital = " + capital);

        return String.format("Continent: %s\nCountry: %s\nCapital: %s\n",
                continent, country, capital);
    }
}
