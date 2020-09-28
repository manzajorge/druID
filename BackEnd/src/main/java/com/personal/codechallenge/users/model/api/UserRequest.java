package com.personal.codechallenge.users.model.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@ApiModel(description = "Api User model")
public class UserRequest {
  
  @ApiModelProperty(notes = "The user's name",
    required = true)
  @JsonProperty("name")
  @NotNull(message = "Name cannot be null")
  private String name;
  
  @ApiModelProperty(notes = "User's birth date")
  @JsonProperty("date")
  @JsonFormat(timezone = "Europe/Madrid", pattern = "yyyy-MM-dd")
  @JsonSerialize(using = ToStringSerializer.class)
  private LocalDate birthDate;
  
  @ApiModelProperty(notes = "User's last name")
  @JsonProperty("last name")
  private String lastName;
  
  @ApiModelProperty(notes = "User's email")
  @JsonProperty("email")
  private String email;
  
}
