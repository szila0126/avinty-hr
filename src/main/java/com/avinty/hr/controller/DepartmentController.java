package com.avinty.hr.controller;

import com.avinty.hr.dto.DepartmentDto;
import com.avinty.hr.service.DepartmentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("department")
public class DepartmentController {
  private final DepartmentsService departmensService;

  public DepartmentController(DepartmentsService departmensService) {
    this.departmensService = departmensService;
  }

  @GetMapping()
  @ResponseBody
  public ResponseEntity<List<DepartmentDto>> getAllDepartments(String name) {
    List<DepartmentDto> allDepartments = departmensService.getAllDepartments(name);
    return new ResponseEntity<>(allDepartments, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> departmentDelete(@PathVariable Long id) {
    departmensService.departmentDelete(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
