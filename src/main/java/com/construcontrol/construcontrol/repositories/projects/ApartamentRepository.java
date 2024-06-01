package com.construcontrol.construcontrol.repositories.projects;

import com.construcontrol.construcontrol.model.domain.projects.Apartament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApartamentRepository extends JpaRepository<Apartament, Long> {
    Apartament getApartamentById(long id);
    Optional<Apartament> getApartamentBySoldStatus(Boolean soldStatus);
    List<Apartament> findByIdConstruction(int idConstruction);
    Apartament deleteApartamentById(long id);
    Optional<Apartament> deleteApartamentBySoldStatus(Boolean soldStatus);
}
