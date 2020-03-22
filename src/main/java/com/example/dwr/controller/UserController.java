package com.example.dwr.controller;

import com.example.dwr.enums.UserType;
import com.example.dwr.servlet.IncludeRule;

import static com.example.dwr.enums.UserType.ADMIN;

/**
 * @author Weijun Yu
 * @since 2020-03-22
 */
public class UserController {

  @IncludeRule
  public UserType getType(String type) {
    System.out.println(type);
    return UserType.valueOf(type);
  }

  @IncludeRule
  public UserType getUser() {
    return UserType.USER;
  }

  public UserType getAdmin() {
    return ADMIN;
  }

}
