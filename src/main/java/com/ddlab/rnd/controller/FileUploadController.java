package com.ddlab.rnd.controller;

import com.ddlab.rnd.entities.Employee;
import com.ddlab.rnd.service.EmpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Api(
    value = "Upload Employee Resume in Organization",
    description = "API for employee resume upload in organisation",
    tags = {"Employee Resume upload"})
@RequestMapping(value = "/upload")
@RestController
public class FileUploadController {

  @Autowired private EmpService empService;

  @ApiOperation(
      value = "Upload Employee Resume",
      tags = {"Employee Resume upload"})
  @ApiResponses(
      value = {
        @ApiResponse(code = 201, response = String.class, message = "Uploaded"),
        @ApiResponse(code = 500, message = "Unable to upload employee's resume")
      })
  @PostMapping(value = "/singleFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile multipartFile) {

    long fileSize = multipartFile.getSize();
    System.out.println("fileSize = " + fileSize);
    System.out.println("File Name = " + multipartFile.getOriginalFilename());
    File file = new File("E:/sure-delete/" + multipartFile.getOriginalFilename());
    try {
      multipartFile.transferTo(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ResponseEntity.ok("File uploaded with size " + fileSize + " successfully");
  }

  @ApiOperation(
      value = "Upload Employee and Resume",
      tags = {"Employee Resume upload"})
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 201,
            response = String.class,
            message = "Emp Created and Resume Uploaded"),
        @ApiResponse(code = 500, message = "Unable to upload employee's resume")
      })
  @PostMapping(
      value = "/empIdAndResume",
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<?> handleEmployeAndResume(
      @RequestPart("file") MultipartFile multipartFile,
      @RequestPart(value = "emp", required = true) String empJsonTxt) {
    System.out.println("multipartFile = " + multipartFile);
    System.out.println("EMP as Json String = " + empJsonTxt);
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      Employee emp = objectMapper.readValue(empJsonTxt, Employee.class);
      System.out.println("Now emp = " + emp);
    } catch (IOException e) {
      e.printStackTrace();
    }
    long fileSize = multipartFile.getSize();
    System.out.println("fileSize = " + fileSize);
    System.out.println("File Name = " + multipartFile.getOriginalFilename());
    File file = new File("E:/sure-delete/" + multipartFile.getOriginalFilename());
    try {
      multipartFile.transferTo(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ResponseEntity.ok("File uploaded with size " + fileSize + " successfully");
  }

  @ApiOperation(
      value = "Upload multiple resumes upload",
      tags = {"Employee Resume upload"})
  @ApiResponses(
      value = {
        @ApiResponse(code = 201, response = String.class, message = "Emp Resume Uploaded"),
        @ApiResponse(code = 500, message = "Unable to upload employee's resume")
      })
  @PostMapping(
      value = "/multiUpload",
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<?> uploadingMultipleFiles(
      @RequestParam("files") MultipartFile[] uploadingFiles) {
    for (MultipartFile uploadedFile : uploadingFiles) {
      System.out.println("Uploaded File Name = " + uploadedFile.getOriginalFilename());
      File file = new File("E:/sure-delete/" + uploadedFile.getOriginalFilename());
      try {
        uploadedFile.transferTo(file);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return ResponseEntity.ok("All Files uploaded successfully ...");
  }
}
