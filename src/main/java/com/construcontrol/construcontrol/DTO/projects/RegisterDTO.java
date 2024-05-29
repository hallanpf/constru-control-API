package com.construcontrol.construcontrol.DTO.projects;

import com.construcontrol.construcontrol.model.domain.users.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole userRole) {
}
