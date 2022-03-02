package com.avinty.hr.controller;

import com.avinty.hr.dto.DepEmpDto;
import com.avinty.hr.service.DepEmpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("dep-emp")
public class DepEmpController {
  private final DepEmpService depEmpService;

  public DepEmpController(DepEmpService depEmpService) {
    this.depEmpService = depEmpService;
  }

  @GetMapping()
  public ResponseEntity<List<DepEmpDto>> getEmployees() {
    List<DepEmpDto> employeesDtos = depEmpService.getAll();
    return new ResponseEntity<>(employeesDtos, HttpStatus.OK);
  }
}
