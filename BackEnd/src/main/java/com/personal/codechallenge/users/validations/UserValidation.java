package com.personal.codechallenge.users.validations;

import com.personal.codechallenge.configuration.UserException;
import com.personal.codechallenge.users.model.api.UserRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class UserValidation {
  
  private static final Integer MIN_AGE = 14;
  
  public void checkBirthDateIsValid(UserRequest user) {
    int years = Period.between(user.getBirthDate(), LocalDate.now()).getYears();
    if (years < MIN_AGE) {
      throw new UserException("Min age must be greater than 14. Please check birth date field");
    }
  }
  
  public void checkNameAndLastNameValid(UserRequest userRequest) {
    if(!StringUtils.isAlphaSpace(userRequest.getLastName()) || !StringUtils.isAlphaSpace(userRequest.getName())) {
      throw new UserException("Name or surname are not in a correct format. Check them, only letters accepted");
    }
  }
}
