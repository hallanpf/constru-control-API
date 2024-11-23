package com.construcontrol.construcontrol.DTO.projects;

import com.construcontrol.construcontrol.model.domain.users.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RegisterDTO {
    private String login;
    private String password;
    private UserRole userRole;
}
