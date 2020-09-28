package com.personal.codechallenge.users.services;

import com.personal.codechallenge.users.model.api.UserRequest;
import com.personal.codechallenge.users.model.api.UserResponse;
import com.personal.codechallenge.users.model.repository.User;
import com.personal.codechallenge.users.repositories.UserRepository;
import com.personal.codechallenge.users.validations.UserValidation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Log4j2
public class UserServiceDefault implements UserService {
  
  private final UserRepository userRepository;
  private final UserValidation userValidation;
  
  @Autowired
  public UserServiceDefault(UserRepository userRepository, UserValidation userValidation) {
    this.userRepository = userRepository;
    this.userValidation = userValidation;
  }
  
  @Override
  @Transactional
  public UserResponse saveUser(UserRequest userRequest) {
  
    userValidation.checkBirthDateIsValid(userRequest);
    userValidation.checkNameAndLastNameValid(userRequest);
  
    User user = userRepository.save(User.builder()
      .birthDate(userRequest.getBirthDate())
      .name(userRequest.getName())
      .lastName(userRequest.getLastName())
      .email(userRequest.getEmail())
      .build());
    log.debug("User added correctly.");
    return UserResponse.builder()
      .id(user.getId())
      .birthDate(user.getBirthDate())
      .name(user.getName())
      .lastName(user.getLastName())
      .email(userRequest.getEmail())
      .build();
  }
  
  @Override
  public List<UserResponse> findAllUsers(String email, LocalDate from, LocalDate to) {
    List<User> list;
    
    if(StringUtils.isNotEmpty(email) && Objects.nonNull(from) && Objects.nonNull(to)) {
      list = userRepository.findAllByEmailContainingAndBirthDateBetween(email, from, to);
    } else if (StringUtils.isNotEmpty(email)) {
      list = userRepository.findAllByEmailContaining(email);
    } else if (Objects.nonNull(from) && Objects.nonNull(to)) {
      list = userRepository.findAllByBirthDateBetween(from, to);
    } else {
      list = userRepository.findAll();
    }
    
    return list
      .stream()
      .map(u -> UserResponse.builder()
        .id(u.getId())
        .birthDate(u.getBirthDate())
        .name(u.getName())
        .lastName(u.getLastName())
        .email(u.getEmail())
        .build())
      .collect(Collectors.toList());
  }
}
