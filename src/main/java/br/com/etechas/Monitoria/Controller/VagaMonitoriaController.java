package br.com.etechas.Monitoria.controller;

import br.com.etechas.Monitoria.dto.VagaMonitoriaResponseDTO;
import br.com.etechas.Monitoria.model.VagaMonitoria;
import br.com.etechas.Monitoria.service.VagaMonitoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/monitorias")
public class VagaMonitoriaController {
    private final VagaMonitoriaService service;

    public VagaMonitoriaController(VagaMonitoriaService service) {
        this.service = service;
    }

    @GetMapping
    public List<VagaMonitoriaResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public VagaMonitoriaResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<VagaMonitoriaResponseDTO> cadastrar(
            @RequestBody VagaMonitoria dados) {
        VagaMonitoriaResponseDTO resposta = service.cadastrar(dados);

        return ResponseEntity
                .created(URI.create("/api/monitorias/" + resposta.id()))
                .body(resposta);
    }

    @PutMapping("/{id}")
    public VagaMonitoriaResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody VagaMonitoria dados) {
        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/inscrever")
    public VagaMonitoriaResponseDTO inscreverAluno(@PathVariable Long id) {
        return service.inscreverAluno(id);
    }
}