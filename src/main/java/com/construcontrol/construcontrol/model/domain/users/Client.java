package com.construcontrol.construcontrol.model.domain.users;

import com.construcontrol.construcontrol.model.domain.users.enums.MaritialStatus;
import com.construcontrol.construcontrol.model.domain.users.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends User {
    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.CLIENTE;
    @Enumerated(EnumType.STRING)
    private MaritialStatus maritalStatus;
}
