package com.personal.codechallenge.users.model.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class UserResponse {
  
  private Integer id;
  
  private String name;
  
  @JsonSerialize(using = ToStringSerializer.class)
  private LocalDate birthDate;
  
  private String lastName;
  
  private String email;
}
