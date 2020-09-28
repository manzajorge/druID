package com.personal.codechallenge.users.services;

import com.personal.codechallenge.users.model.api.UserRequest;
import com.personal.codechallenge.users.model.api.UserResponse;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
  
  UserResponse saveUser(UserRequest userRequest);
  
  List<UserResponse> findAllUsers(String email, LocalDate from, LocalDate to);
}
