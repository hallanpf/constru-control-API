package com.construcontrol.construcontrol.repositories.projects;

import com.construcontrol.construcontrol.model.domain.projects.Construction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConstructionRepository extends JpaRepository<Construction, Long> {
    Construction getConstructionById(long id);
    Optional<Construction> getConstructionByCnpj(String cnpj);

    Construction deleteConstructionById(Long id);
    Optional<Construction> deleteConstructionByCnpj(String cnpj);
}
