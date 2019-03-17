package com.ddlab.rnd.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Api(
    value = "Download File",
    description = "API for file download",
    tags = {"File download"})
@RequestMapping(value = "/download")
@RestController
public class FileDownloadController {

  @ApiOperation(
      value = "Download file",
      tags = {"File download"})
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, response = String.class, message = "Downloaded"),
        @ApiResponse(code = 500, message = "Unable to download employee's resume")
      })
  @PostMapping(value = "/file", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public ResponseEntity<?> downloadFile(@RequestParam("fileName") String fileName) {
    String dirPath = "E:/sure-delete/";
    byte[] fileBytes = null;
    try {
      fileBytes = Files.readAllBytes(Paths.get(dirPath + fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
        .body(fileBytes);
  }

  @ApiOperation(
      value = "Show file",
      tags = {"File download"})
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, response = String.class, message = "Show"),
        @ApiResponse(code = 500, message = "Unable to show employee's resume")
      })
  @GetMapping(value = "/showImage", produces = "image/jpg")
  public ResponseEntity<byte[]> getImageAsResponseEntity(
      @RequestParam("fileName") String fileName) {
    String dirPath = "E:/sure-delete/";
    HttpHeaders headers = new HttpHeaders();
    InputStream in = null;
    byte[] media = new byte[0];
    try {
      in = new FileInputStream(dirPath + fileName);
      media = IOUtils.toByteArray(in);
    } catch (IOException e) {
      e.printStackTrace();
    }
    ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
    return responseEntity;
  }
}
