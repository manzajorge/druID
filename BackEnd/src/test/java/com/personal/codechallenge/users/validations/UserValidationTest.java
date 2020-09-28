package com.personal.codechallenge.users.validations;

import com.personal.codechallenge.configuration.UserException;
import com.personal.codechallenge.users.model.api.UserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class UserValidationTest {
  @InjectMocks
  private UserValidation userValidation;
  
  @Test
  public void checkBirthDateIsValid() {
    UserRequest inputUserRequest = UserRequest.builder()
      .birthDate(LocalDate.of(LocalDate.now().minusYears(18).getYear(), 5, 5))
      .build();
    
    userValidation.checkBirthDateIsValid(inputUserRequest);
  }
  
  @Test(expected = UserException.class)
  public void checkBirthDateInvalid() {
    UserRequest inputUserRequest = UserRequest.builder()
      .birthDate(LocalDate.of(LocalDate.now().minusYears(10).getYear(), 5, 5))
      .build();
    
    userValidation.checkBirthDateIsValid(inputUserRequest);
  }
  
  @Test
  public void checkNameAndLastNameValid() {
    UserRequest inputUserRequest = UserRequest.builder()
      .name("Hello World")
      .lastName("Last Name")
      .build();
    
    userValidation.checkNameAndLastNameValid(inputUserRequest);
  }
  
  @Test(expected = UserException.class)
  public void checkNameInvalid() {
    UserRequest inputUserRequest = UserRequest.builder()
      .name("Hello World837££")
      .lastName("Last Name")
      .build();
    
    userValidation.checkNameAndLastNameValid(inputUserRequest);
  }
  
  @Test(expected = UserException.class)
  public void checkLastNameInvalid() {
    UserRequest inputUserRequest = UserRequest.builder()
      .name("Hello World")
      .lastName("Last Name %%%")
      .build();
    
    userValidation.checkNameAndLastNameValid(inputUserRequest);
  }
  
  @Test(expected = UserException.class)
  public void checkNameAndLastNameInvalid() {
    UserRequest inputUserRequest = UserRequest.builder()
      .name("Hello World %%^^34234")
      .lastName("3333")
      .build();
    
    userValidation.checkNameAndLastNameValid(inputUserRequest);
  }
  
}
