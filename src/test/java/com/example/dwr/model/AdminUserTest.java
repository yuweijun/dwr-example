package com.example.dwr.model;

import com.example.dwr.util.Introspectors;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

/**
 * @author Weijun Yu
 * @since 2020-03-23
 */
public class AdminUserTest {

  @Test
  public void test() throws Exception {
    AdminUser user = new AdminUser();
    user.setId(1);
    user.setName("name 1");
    user.setCreatedAt(new Date());
    Map<String, Object> introspect = Introspectors.introspect(user);

    System.out.println(introspect);
  }
}