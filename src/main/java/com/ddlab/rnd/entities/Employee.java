package com.ddlab.rnd.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
  @ApiModelProperty(required = true, example = "123")
  private int empId;

  @ApiModelProperty(required = true, example = "Deb")
  private String firstName;

  @ApiModelProperty(required = true, example = "Mishra")
  private String lastName;
}
