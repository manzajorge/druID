package com.personal.codechallenge.users.sorting;

import lombok.Getter;

@Getter
public enum UserSortField {
  
  NONE(""),
  AMOUNT("amount");
  
  private final String code;
  
  UserSortField(final String code) {
    this.code = code;
  }
  
}