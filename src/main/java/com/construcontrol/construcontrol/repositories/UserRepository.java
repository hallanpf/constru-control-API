package com.construcontrol.construcontrol.repositories;

import com.construcontrol.construcontrol.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User getUsersById(long id);
}
