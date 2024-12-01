package com.construcontrol.construcontrol.DTO.projects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AuthenticationDTO {
    private String login;
    private String password;
}
