package com.personal.codechallenge.utils;

import lombok.Getter;

@Getter
public enum SortType {
  
  ASC("ASC"),
  DESC("DESC");
  
  private final String code;
  
  SortType(String code) {
    this.code = code;
  }
  
}
