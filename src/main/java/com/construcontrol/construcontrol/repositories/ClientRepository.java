package com.construcontrol.construcontrol.repositories;

import com.construcontrol.construcontrol.model.domain.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Clients, Long> {

    Clients getClientsById(long id);
    Optional<Clients> getClientsByCpf(String cpf);


}

