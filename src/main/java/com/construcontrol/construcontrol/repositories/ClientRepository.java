package com.construcontrol.construcontrol.repositories;

import com.construcontrol.construcontrol.model.domain.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

interface ClientRepository extends JpaRepository<Clients, Long> {

    Clients getClientsById(long id);

}

