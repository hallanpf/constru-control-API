package com.construcontrol.construcontrol.services.projects;

import com.construcontrol.construcontrol.DTO.projects.ApartamentDTO;
import com.construcontrol.construcontrol.DTO.projects.CompanyDTO;
import com.construcontrol.construcontrol.DTO.projects.ConstructionDTO;
import com.construcontrol.construcontrol.model.domain.projects.Apartament;
import com.construcontrol.construcontrol.model.domain.projects.Company;
import com.construcontrol.construcontrol.model.domain.projects.Construction;
import com.construcontrol.construcontrol.repositories.projects.ApartamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ApartamentService {
    private final ApartamentRepository apartamentRepository;

    public ApartamentDTO criarApartament(ApartamentDTO apartamentDTO) {
        Apartament apartament = convertToEntity(apartamentDTO);
        return convertToDTO(apartamentRepository.save(apartament));
    }

    public List<ApartamentDTO> listarApartaments() {
        return apartamentRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ApartamentDTO> buscarApartamentPorId(Long id) {
        return apartamentRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Optional<ApartamentDTO> atualizarApartament(Long id, ApartamentDTO apartamentDTO) {
        return apartamentRepository.findById(id).map(apartament -> {
//            apartament.setConstruction(convertToEntity(apartamentDTO.getConstruction()));
            apartament.setNumber(apartamentDTO.getNumber());
            apartament.setArea(apartamentDTO.getArea());
            apartament.setPrice(apartamentDTO.getPrice());
            apartament.setSoldStatus(apartamentDTO.isSoldStatus());
            return convertToDTO(apartamentRepository.save(apartament));
        });
    }

    public boolean deletarApartament(Long id) {
        if (apartamentRepository.existsById(id)) {
            apartamentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ApartamentDTO convertToDTO(Apartament apartament) {
        return new ApartamentDTO(
                apartament.getId(),
//                apartament.getConstruction() !=null ? new ConstructionDTO(
//                        apartament.getConstruction().getId(),
//                        apartament.getConstruction().getConstruction(),
//                        apartament.getConstruction().getCnpj(),
//                        apartament.getConstruction().getStartDate(),
//                        apartament.getConstruction().getEndDate(),
//                        apartament.getConstruction().getCompany() != null ? new CompanyDTO(
//                                construction.getCompany().getId(),
//                                construction.getCompany().getCompany(),
//                                construction.getCompany().getCnpj()) :null) : null,
                apartament.getNumber(),
                apartament.getArea(),
                apartament.getPrice(),
                apartament.isSoldStatus(),
                apartament.getIdClient()
        );
    }

    private Apartament convertToEntity(ApartamentDTO apartamentDTO) {
        Apartament apartament = new Apartament();
        apartament.setId(apartamentDTO.getId());
//        apartament.setConstruction(convertToEntity(apartamentDTO.getConstruction()));
        apartament.setNumber(apartamentDTO.getNumber());
        apartament.setArea(apartamentDTO.getArea());
        apartament.setPrice(apartamentDTO.getPrice());
        apartament.setSoldStatus(apartamentDTO.isSoldStatus());
        apartament.setIdClient(apartamentDTO.getIdClient());
        return apartament;
    }

//    private Construction convertToEntity(ConstructionDTO constructionDTO) {
//        Construction construction = new Construction();
//        construction.setId(constructionDTO.getId());
//        construction.setConstruction(constructionDTO.getConstruction());
//        construction.setCnpj(constructionDTO.getCnpj());
//        construction.setStartDate(constructionDTO.getStartDate());
//        construction.setEndDate(constructionDTO.getEndDate());
//        construction.setCompany((convertToEntity(constructionDTO.getCompany())));
//        return construction;
//    }
//
//    private Company convertToEntity(CompanyDTO companyDTO) {
//        Company company = new Company();
//        company.setId(companyDTO.getId());
//        company.setCompany(company.getCompany());
//        company.setCnpj(companyDTO.getCnpj());
//        return company;
//    }
}
