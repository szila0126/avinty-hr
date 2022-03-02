package com.avinty.hr.controller;

import com.avinty.hr.config.util.JwtUtil;
import com.avinty.hr.dto.EmployeesDto;
import com.avinty.hr.dto.EmployeesInsertDto;
import com.avinty.hr.error.exception.InvalidUserNameOrPasswordException;
import com.avinty.hr.service.EmployeesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
@CrossOrigin
public class EmployeesController {
  private final EmployeesService employeesService;
  private final AuthenticationManager authenticateManager;
  private final JwtUtil jwtUtil;

  public EmployeesController(
      EmployeesService employeesService,
      AuthenticationManager authenticateManager,
      JwtUtil jwtUtil) {
    this.employeesService = employeesService;
    this.authenticateManager = authenticateManager;
    this.jwtUtil = jwtUtil;
  }

  @GetMapping()
  public ResponseEntity<List<EmployeesDto>> getEmployees() {
    List<EmployeesDto> employeesDtos = employeesService.getAll();
    return new ResponseEntity<>(employeesDtos, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<EmployeesDto> create(@RequestBody @Validated EmployeesInsertDto dto) {
    return ResponseEntity.ok(this.employeesService.create(dto));
  }

  @PostMapping("/authenticate")
  public String generateToken(@RequestBody EmployeesDto authRequest) {
    try {
      authenticateManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              authRequest.getEmail(), authRequest.getPassword()));
    } catch (Exception ex) {
      throw new InvalidUserNameOrPasswordException();
    }
    employeesService.activate(authRequest);
    return jwtUtil.generateToken(authRequest.getEmail());
  }
}
