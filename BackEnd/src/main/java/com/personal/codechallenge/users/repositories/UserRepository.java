package com.personal.codechallenge.users.repositories;


import com.personal.codechallenge.users.model.repository.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  List<User> findAllByEmailContaining(String email);
  List<User> findAllByEmailContainingAndBirthDateBetween(String email, LocalDate from, LocalDate to);
  List<User> findAllByBirthDateBetween(LocalDate from, LocalDate to);
}
