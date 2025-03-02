package com.personal.codechallenge.users.services;

import com.personal.codechallenge.users.model.api.UserRequest;
import com.personal.codechallenge.users.model.api.UserResponse;
import com.personal.codechallenge.users.model.repository.User;
import com.personal.codechallenge.users.repositories.UserRepository;
import com.personal.codechallenge.users.validations.UserValidation;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceDefaultTest {
  
  @Mock
  private UserRepository userRepository;
  
  @Mock
  private UserValidation userValidation;
  
  @InjectMocks
  private UserServiceDefault userService;
  
  @Before
  public void setUp() {
    userService = new UserServiceDefault(
      userRepository, userValidation
    );
  }
  
  @Test
  public void saveUser() {
  
    UserRequest userTest = UserRequest.builder()
      .birthDate(LocalDate.now())
      .name("Pedro")
      .lastName("Seguro")
      .email("this is an email")
      .build();
  
    User userMock = User.builder()
      .birthDate(LocalDate.now())
      .name("Pedro")
      .lastName("Seguro")
      .email("this is an email")
      .build();
  
    when(userRepository.save(any(User.class))).thenReturn(userMock);
    doNothing().when(userValidation).checkNameAndLastNameValid(userTest);
    doNothing().when(userValidation).checkBirthDateIsValid(userTest);
  
    UserResponse actual = userService.saveUser(userTest);
  
    verify(userRepository).save(any(User.class));
    verify(userValidation).checkNameAndLastNameValid(userTest);
    verify(userValidation).checkBirthDateIsValid(userTest);
    assertEquals(userTest.getBirthDate(), actual.getBirthDate());
    assertEquals(userTest.getName(), actual.getName());
    assertEquals(userTest.getLastName(), actual.getLastName());
    assertEquals(userTest.getBirthDate(), actual.getBirthDate());
  }
  
  @Test(expected = Exception.class)
  public void saveUserWithoutName() {
  
    UserRequest userTest = UserRequest.builder()
      .birthDate(LocalDate.now())
      .lastName("Seguro")
      .email("this is an email")
      .build();
  
    when(userRepository.save(any(User.class))).thenThrow(new Exception("Account IBAN cannot be null"));
  
    userService.saveUser(userTest);
  
    verify(userRepository).save(any(User.class));
  }
  
  @Test(expected = Exception.class)
  public void saveUserWithoutEmail() {
  
    UserRequest userTest = UserRequest.builder()
      .birthDate(LocalDate.now())
      .name("Pedro")
      .lastName("Seguro")
      .build();
  
    when(userRepository.save(any(User.class))).thenThrow(new Exception("Email cannot be null"));
  
    userService.saveUser(userTest);
  
    verify(userRepository).save(any(User.class));
  }
  
  @Test
  public void deleteUsers() {
    
    List<Integer> usersId = Lists.newArrayList(1,2,3,4,5);
    
    doNothing().when(userRepository).deleteAllByIdIn(anyList());
    
    userService.deleteUsers(usersId);
    
    verify(userRepository).deleteAllByIdIn(anyList());
  }
  
}