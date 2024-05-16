package com.construcontrol.construcontrol.repositories;

import com.construcontrol.construcontrol.model.domain.projects.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
