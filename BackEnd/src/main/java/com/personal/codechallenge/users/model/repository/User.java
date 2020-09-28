package com.personal.codechallenge.users.model.repository;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "user_table")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
  
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @Column(name = "name")
  @NotNull
  private String name;
  
  @Column(name = "birthdate")
  private LocalDate birthDate;
  
  @Column(name = "lastname")
  @NotNull
  private String lastName;
  
  @Column(name = "email")
  @Email(message = "Email must be in a correct format")
  private String email;
  
}
