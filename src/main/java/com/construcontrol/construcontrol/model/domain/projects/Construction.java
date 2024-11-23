package com.construcontrol.construcontrol.model.domain.projects;

import com.construcontrol.construcontrol.shared.Address;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Table(name = "constructions")
public class Construction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "construction")
    private String construction;
    @Column(name = "cnpj")
    private String cnpj;
    @Column(name="start_date")
    private Date startDate;
    @Column(name="end_date")
    private Date endDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", nullable = false, referencedColumnName = "id")
    private Company company;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "construction", cascade = CascadeType.ALL)
    private List<Apartament> apartamentsList;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
