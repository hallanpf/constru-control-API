package com.construcontrol.construcontrol.repositories;

import com.construcontrol.construcontrol.model.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Manager getManagerById(long id);
    Optional<Manager> getManagerByCpf(String cpf);


}
