package com.construcontrol.construcontrol.model.domain.users.enums;

public enum UserRole {
  GESTOR("GESTOR"),

  CLIENTE("CLIENTE"),

  CORRETOR("CORRETOR");

  private final String role;

  UserRole(String role) {
    this.role = role;
  }

  public String getUserRole() {
    return role;
  }
}
