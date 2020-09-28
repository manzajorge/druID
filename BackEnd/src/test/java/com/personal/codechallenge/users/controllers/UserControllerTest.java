package com.personal.codechallenge.users.controllers;

import com.personal.codechallenge.users.model.api.UserRequest;
import com.personal.codechallenge.users.model.api.UserResponse;
import com.personal.codechallenge.users.services.UserService;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
  
  @Mock
  private UserService userService;
  
  @InjectMocks
  private UserController userController;
  
  @Before
  public void setUp() {
    userController = new UserController(
      userService
    );
  }
  
  /*
  * Save user successfully
  * */
  @Test
  public void saveUser() {
    UserRequest userTest = UserRequest.builder()
      .birthDate(LocalDate.now())
      .name("Pedro")
      .lastName("Seguro")
      .email("this is an email")
      .build();
    UserResponse userMock = UserResponse.builder()
      .birthDate(LocalDate.now())
      .name("Pedro")
      .lastName("Seguro")
      .email("this is an email")
      .build();
    when(userService.saveUser(any(UserRequest.class))).thenReturn(userMock);
  
    ResponseEntity<UserResponse> actual = userController.saveUser(userTest);
  
    assertEquals(userTest.getBirthDate(), actual.getBody().getBirthDate());
    assertEquals(userTest.getName(), actual.getBody().getName());
    assertEquals(userTest.getLastName(), actual.getBody().getLastName());
    assertEquals(userTest.getEmail(), actual.getBody().getEmail());
  
    verify(userService).saveUser(any(UserRequest.class));
  }
  
  /*
   * Try to save user without name
   * */
  @Test(expected = Exception.class)
  public void saveUserWhenMissingName() {
    UserRequest userTest = UserRequest.builder()
      .birthDate(LocalDate.now())
      .lastName("Seguro")
      .email("this is an email")
      .build();
  
    when(userService.saveUser(any(UserRequest.class))).thenThrow(new Exception("Error database (name cannot be null"));
  
    userController.saveUser(userTest);
  
    verify(userService).saveUser(any(UserRequest.class));
  }
  
  /*
   * Try to save user without email
   * */
  @Test(expected = Exception.class)
  public void saveUserWhenMissingEmail() {
    UserRequest userTest = UserRequest.builder()
      .birthDate(LocalDate.now())
      .name("Pedro")
      .lastName("Seguro")
      .build();
  
    when(userService.saveUser(any(UserRequest.class))).thenThrow(new Exception("Error database (email cannot be null)"));
  
    userController.saveUser(userTest);
  
    verify(userService).saveUser(any(UserRequest.class));
  }
  
  @Test
  public void searchUsers() {
    List<UserResponse> users = Lists.newArrayList(
      UserResponse.builder()
        .birthDate(LocalDate.now())
        .name("Pedro")
        .lastName("Seguro")
        .email("this is an email")
        .build(),
      UserResponse.builder()
        .birthDate(LocalDate.now())
        .name("Pepe")
        .lastName("Seguro 1")
        .email("this is an email 1")
        .build(),
      UserResponse.builder()
        .birthDate(LocalDate.now())
        .name("test name")
        .lastName("Seguro 2")
        .email("this is an email 2")
        .build()
    );
    
    when(userService.findAllUsers(null, null, null)).thenReturn(users);
  
    ResponseEntity<List<UserResponse>> actual = userController.getUsers(null, null, null);
  
    assertFalse(actual.getBody().isEmpty());
    assertEquals(users.size(), actual.getBody().size());
    assertEquals(users.get(0).getBirthDate(), actual.getBody().get(0).getBirthDate());
    assertEquals(users.get(0).getEmail(), actual.getBody().get(0).getEmail());
    assertEquals(users.get(0).getLastName(), actual.getBody().get(0).getLastName());
    verify(userService).findAllUsers(null, null, null);
  }
  
  @Test
  public void findUsersFilteredByEmail() {
    List<UserResponse> users = Lists.newArrayList(
      UserResponse.builder()
        .birthDate(LocalDate.now())
        .name("Pedro")
        .lastName("Seguro")
        .email("this is an email")
        .build(),
      UserResponse.builder()
        .birthDate(LocalDate.now())
        .name("Pepe")
        .lastName("Seguro 1")
        .email("this is an email 1")
        .build(),
      UserResponse.builder()
        .birthDate(LocalDate.now())
        .name("test name")
        .lastName("Seguro 2")
        .email("this is an email 2")
        .build()
    );
    String emailFilter = "this";
    List<UserResponse> userExpected = users.stream().filter(u -> u.getEmail().contains(emailFilter)).collect(Collectors.toList());
    int expectedResultSize = 3;
  
    when(userService.findAllUsers(emailFilter, null, null)).thenReturn(
      users.stream().filter(u -> u.getEmail().contains(emailFilter)).collect(Collectors.toList()));
  
    ResponseEntity<List<UserResponse>> actual = userController.getUsers(emailFilter, null, null);
  
    assertFalse(actual.getBody().isEmpty());
    assertEquals(expectedResultSize, actual.getBody().size());
    verify(userService).findAllUsers(emailFilter, null, null);
  }
  
  @Test
  public void deleteUsers(){
    List<Integer> listIds = Lists.newArrayList(1,2,3,4);
    
    doNothing().when(userService).deleteUsers(listIds);
    
    userController.deleteUsers(listIds);
    
    verify(userService).deleteUsers(listIds);
    
  }
}