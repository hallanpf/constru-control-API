package com.construcontrol.construcontrol.services.projects;

import com.construcontrol.construcontrol.DTO.projects.CompanyDTO;
import com.construcontrol.construcontrol.model.domain.projects.Company;
import com.construcontrol.construcontrol.repositories.projects.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyDTO criarCompany(CompanyDTO companyDTO) {
        Company company = convertToEntity(companyDTO);
        return convertToDTO(companyRepository.save(company));
    }

    public List<CompanyDTO> listarCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CompanyDTO> buscarCompanyPorId(Long id) {
        return companyRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Optional<CompanyDTO> atualizarCompany(Long id, CompanyDTO companyDTO) {
        return companyRepository.findById(id).map(company -> {
            company.setCompany(company.getCompany());
            company.setCnpj(companyDTO.getCnpj());
            return convertToDTO(companyRepository.save(company));
        });
    }

    public boolean deletarCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private CompanyDTO convertToDTO(Company company) {
        return new CompanyDTO(
                company.getId(),
                company.getCompany(),
                company.getCnpj()
        );
    }

    private Company convertToEntity(CompanyDTO companyDTO) {
        Company company = new Company();
        company.setId(companyDTO.getId());
        company.setCompany(company.getCompany());
        company.setCnpj(companyDTO.getCnpj());
        return company;
    }

}
