package com.personal.codechallenge.utils;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ApiModel(description = "Api sort search request model")
@EqualsAndHashCode
public class SortSearch<T> {
  
  public static final String DEFAULT_SORT_FIELD = "reference";
  
  private T field;
  private SortType type;
  
}