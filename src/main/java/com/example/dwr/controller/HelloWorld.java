package com.example.dwr.controller;

import com.example.dwr.model.User;
import com.example.dwr.vo.UserVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

  public User hello6(String name) {
    System.out.println("Hello6! " + name);

    User user = new User();
    user.setId(1);
    user.setName("name");
    user.setCreatedAt(new Date());
    return user;
  }

  public User[] hello7(String name) {
    System.out.println("Hello7! " + name);

    User user = new User();
    user.setId(1);
    user.setName("name");
    user.setCreatedAt(new Date());
    return new User[]{user};
  }

  public List<User> hello8(String name) {
    System.out.println("Hello8! " + name);

    User user = new User();
    user.setId(1);
    // user.setName("name");
    user.setCreatedAt(new Date());
    List<User> users = new ArrayList<>();
    users.add(user);
    return users;
  }
}
