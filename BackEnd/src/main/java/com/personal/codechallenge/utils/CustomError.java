package com.personal.codechallenge.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CustomError {
  private int httpCode;
  private String message;
}
