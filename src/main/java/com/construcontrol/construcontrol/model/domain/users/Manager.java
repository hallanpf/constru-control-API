package com.construcontrol.construcontrol.model.domain.users;

import com.construcontrol.construcontrol.model.domain.users.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Manager extends User {
    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.GESTOR;
}
