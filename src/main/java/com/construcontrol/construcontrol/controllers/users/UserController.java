package com.construcontrol.construcontrol.controllers.users;

import com.construcontrol.construcontrol.repositories.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserRepository userRepository;

  @GetMapping
  public ResponseEntity getAllUsers() {
    var allUsers = userRepository.findAll();
    return ResponseEntity.ok(allUsers);
  }

}
