package com.ddlab.rnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ddlab.rnd.*"})
public class DDLABSpringBootApplication {
  public static void main(String[] args) {
    SpringApplication.run(DDLABSpringBootApplication.class, args);
  }
}
