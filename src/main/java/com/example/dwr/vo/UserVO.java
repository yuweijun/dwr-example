package com.example.dwr.vo;

import java.util.Date;

/**
 * @author Weijun Yu
 * @since 2020-03-21
 */
public class UserVO {

  private int id;

  private String name;

  private Date createdAt;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
