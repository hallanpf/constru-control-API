package com.construcontrol.construcontrol.model.domain.users;

import com.construcontrol.construcontrol.model.domain.users.enums.UserType;
import com.construcontrol.construcontrol.shared.Address;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Entity

@Table(name = "users")
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    protected String name;
    @Column(name = "cpf", unique = true, nullable = false, length = 11)
    protected String cpf;
    @Column(name = "rg", unique = true, nullable = false, length = 9)
    protected String rg;
    @Column(name = "phone")
    protected String phone;
    @Column(unique = true, name = "email")
    protected String email;
    @Enumerated(EnumType.STRING)
    UserType userType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    protected Address address;

}
