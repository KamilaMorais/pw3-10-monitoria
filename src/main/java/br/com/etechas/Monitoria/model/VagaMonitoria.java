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
    private Long id;

    @Column(nullable = false)
    private String disciplina;

    @Column(name = "nome_monitor", nullable = false)
    private String nomeMonitor;

    @Column(name = "vagas_totais", nullable = false)
    private Integer vagasTotais;

    @Column(name = "vagas_ocupadas", nullable = false)
    private Integer vagasOcupadas = 0;

    @Column(nullable = false)
    private Boolean aberta = true;
}
