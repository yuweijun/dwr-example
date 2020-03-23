package com.example.dwr.model;

/**
 * @author Weijun Yu
 * @since 2020-03-23
 */
public class AdminUser extends User {

  private boolean admin;

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

}
