package com.construcontrol.construcontrol.shared;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Table(name = "documents")

public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "alvara", nullable = false)
    private String alvara;
    @Column(name = "licenca_instalacao", nullable = false)
    private String licencaInstalacao;
    @Column(name = "habite_se",  nullable = false)
    private String habiteSe;

    public Documents(DocumentsDTO documentsDTO) {
        this.alvara = documentsDTO.alvara();
        this.licencaInstalacao = documentsDTO.licencaInstalacao();
        this.habiteSe = documentsDTO.habiteSe();
    }

    public void update(DocumentsDTO documentsDTO) {
        this.alvara = documentsDTO.alvara();
        this.licencaInstalacao = documentsDTO.licencaInstalacao();
        this.habiteSe = documentsDTO.habiteSe();
    }
}

// alvará, licença de instalação, habite-se