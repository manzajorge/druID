package com.personal.codechallenge.users.model.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class User {
  
  private Integer id;
  
  private String name;
  
  private LocalDate birthDate;
  
  private String lastName;
  
  private String email;
}
