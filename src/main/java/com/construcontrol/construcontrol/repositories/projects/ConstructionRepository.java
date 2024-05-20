package com.construcontrol.construcontrol.repositories.projects;

import com.construcontrol.construcontrol.model.domain.projects.Construction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConstructionRepository extends JpaRepository<Construction, Long> {
}
