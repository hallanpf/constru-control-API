package com.construcontrol.construcontrol.repositories.users;

import com.construcontrol.construcontrol.model.domain.users.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager getManagerById(long id);
    Optional<Manager> getManagerByCpf(String cpf);

    Manager deleteManagerById(Long id);
    Optional<Manager> deleteManagerByCpf(String cpf);
}
