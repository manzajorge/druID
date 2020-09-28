package com.personal.codechallenge.users.controllers;

import com.personal.codechallenge.users.model.api.UserRequest;
import com.personal.codechallenge.users.model.api.UserResponse;
import com.personal.codechallenge.users.services.UserService;
import com.personal.codechallenge.utils.ApiMessages;
import com.personal.codechallenge.utils.CustomError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@Validated
@RequestMapping(value = "/api/users")
@Api(tags = "Users API", value = "Operations for Users API")
public class UserController {
  
  private final UserService userService;
  
  @Autowired
  public UserController(UserService userService){
    this.userService = userService;
  }
  
  @ResponseStatus(HttpStatus.OK)
  @PostMapping(
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ApiOperation(value = "Create User operation")
  @ApiResponses({
    @ApiResponse(
      code = HttpServletResponse.SC_NO_CONTENT,
      message = ApiMessages.OK_RESPONSE_MESSAGE
    ),
    @ApiResponse(
      code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
      message = ApiMessages.GENERIC_ERROR_RESPONSE_MESSAGE,
      response = CustomError.class
    )
  })
  public ResponseEntity<UserResponse> saveUser(
    @Valid @RequestBody UserRequest userRequest
  ) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userRequest));
  }
  
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ApiOperation(value = "Get list of users")
  @ApiResponses({
    @ApiResponse(
      code = HttpServletResponse.SC_OK,
      message = ApiMessages.OK_RESPONSE_MESSAGE,
      response = UserResponse[].class
    ),
    @ApiResponse(
      code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
      message = ApiMessages.GENERIC_ERROR_RESPONSE_MESSAGE,
      response = CustomError.class
    )
  })
  public ResponseEntity<List<UserResponse>> getUsers(
    @RequestParam(value = "email", required = false, defaultValue = "") final String email,
    @RequestParam(value = "start", required = false, defaultValue = "")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate from,
    @RequestParam(value = "to", required = false, defaultValue = "")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate to
  ) {
    return ResponseEntity.ok(this.userService.findAllUsers(email, from, to));
  }
  
}
