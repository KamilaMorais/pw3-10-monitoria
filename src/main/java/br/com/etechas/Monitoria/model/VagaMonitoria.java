package br.com.etechas.Monitoria.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vaga_monitoria")
@Getter
@Setter
@NoArgsConstructor
public class VagaMonitoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "disciplina", nullable = false)
    private String disciplina;

    @Column(name = "nomeMonitor", nullable = false)
    private String nomeMonitor;

    @Column(name = "vagasTotais", nullable = false)
    private Integer vagasTotais;

    @Column(name = "vagasOcupadas", nullable = false)
    private Integer vagasOcupadas = 0;

    @Column(name = "aberta", nullable = false)
    private Boolean aberta = true;
}
