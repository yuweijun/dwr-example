package com.example.dwr.controller;

import com.example.dwr.enums.UserType;
import com.example.dwr.servlet.IncludeRule;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.example.dwr.enums.UserType.ADMIN;
import static com.example.dwr.enums.UserType.USER;

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
    return USER;
  }

  /**
   * http://directwebremoting.org/dwr/documentation/server/javaapi.html
   */
  @IncludeRule
  public List<UserType> getAll(boolean includeAdmin, HttpServletRequest request, ServletContext servletContext) {
    System.out.println(includeAdmin);

    String requestURI = request.getRequestURI();
    System.out.println(request.getClass().getName());
    System.out.println(requestURI);

    String serverInfo = servletContext.getServerInfo();
    System.out.println(servletContext.getClass().getName());
    System.out.println(serverInfo);

    List<UserType> list = new ArrayList<>();
    list.add(USER);

    if (includeAdmin) {
      list.add(ADMIN);
    }

    return list;
  }

  /**
   * This method will not export to javascript of userController
   */
  public UserType getAdmin() {
    return ADMIN;
  }

}
