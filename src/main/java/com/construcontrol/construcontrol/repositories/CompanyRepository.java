package com.construcontrol.construcontrol.repositories;

import com.construcontrol.construcontrol.model.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, long> {

}
