package com.construcontrol.construcontrol.repositories;

import com.construcontrol.construcontrol.model.domain.Construction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConstructionRepository extends JpaRepository<Construction, long> {

}
