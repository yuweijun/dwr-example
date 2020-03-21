package com.example.dwr.controller;

import com.example.dwr.vo.UserVO;

import java.util.Date;

/**
 * @author Weijun Yu
 * @since 2020-03-21
 */
public class HelloWorld {

  public void hello1() {
    System.out.println("Hello1!");
  }

  public void hello2(String name) {
    System.out.println("Hello2! " + name);
  }

  public String hello3() {
    System.out.println("Hello3!");
    return "Hello3!";
  }

  public String hello4(String name) {
    System.out.println("Hello4! " + name);
    return "Hello4! " + name;
  }

  public UserVO hello5(String name) {
    System.out.println("Hello5! " + name);

    UserVO user = new UserVO();
    user.setId(1);
    user.setName("name");
    user.setCreatedAt(new Date());
    return user;
  }

}
