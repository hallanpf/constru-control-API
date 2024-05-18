package com.construcontrol.construcontrol.repositories;

import com.construcontrol.construcontrol.model.domain.projects.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company getCompanyById(long id);
    Optional<Company> getManagerByCnpj(String cnpj);

    Company deleteCompanyById(long id);
    Optional<Company> deleteCompanyByCnpj(String cnpj);
}
