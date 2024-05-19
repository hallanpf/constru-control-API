package com.construcontrol.construcontrol.repositories.users;

import com.construcontrol.construcontrol.model.domain.users.Broker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrokerRepository extends JpaRepository<Broker, Long> {
}
