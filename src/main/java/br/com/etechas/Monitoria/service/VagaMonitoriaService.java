package br.com.etechas.Monitoria.service;

import br.com.etechas.Monitoria.dto.VagaMonitoriaResponseDTO;
import br.com.etechas.Monitoria.model.VagaMonitoria;
import br.com.etechas.Monitoria.repository.VagaMonitoriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VagaMonitoriaService {
    private final VagaMonitoriaRepository repository;

    public VagaMonitoriaService(VagaMonitoriaRepository repository) {
        this.repository = repository;
    }

    public List<VagaMonitoriaResponseDTO> listar() {
        return repository.findAll().stream()
                .map(this::converter)
                .toList();
    }

    public VagaMonitoriaResponseDTO buscarPorId(Long id) {
        return converter(buscarEntidade(id));
    }

    public VagaMonitoriaResponseDTO cadastrar(VagaMonitoria dados) {
        validar(dados);

        VagaMonitoria monitoria = new VagaMonitoria();
        monitoria.setDisciplina(dados.getDisciplina().trim());
        monitoria.setNomeMonitor(dados.getNomeMonitor().trim());
        monitoria.setVagasTotais(dados.getVagasTotais());
        monitoria.setVagasOcupadas(0);
        monitoria.setAberta(true);

        return converter(repository.save(monitoria));
    }

    public VagaMonitoriaResponseDTO atualizar(Long id, VagaMonitoria dados) {
        validar(dados);
        VagaMonitoria monitoria = buscarEntidade(id);

        if (dados.getVagasTotais() < monitoria.getVagasOcupadas()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "O total de vagas não pode ser menor que as vagas ocupadas.");
        }

        monitoria.setDisciplina(dados.getDisciplina().trim());
        monitoria.setNomeMonitor(dados.getNomeMonitor().trim());
        monitoria.setVagasTotais(dados.getVagasTotais());
        monitoria.setAberta(
                monitoria.getVagasOcupadas() < monitoria.getVagasTotais());

        return converter(repository.save(monitoria));
    }

    public void excluir(Long id) {
        VagaMonitoria monitoria = buscarEntidade(id);
        repository.delete(monitoria);
    }

    public VagaMonitoriaResponseDTO inscreverAluno(Long id) {
        VagaMonitoria monitoria = buscarEntidade(id);

        if (!Boolean.TRUE.equals(monitoria.getAberta())
                || monitoria.getVagasOcupadas() >= monitoria.getVagasTotais()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Inscrições encerradas para esta monitoria.");
        }

        monitoria.setVagasOcupadas(monitoria.getVagasOcupadas() + 1);

        if (monitoria.getVagasOcupadas().equals(monitoria.getVagasTotais())) {
            monitoria.setAberta(false);
        }

        return converter(repository.save(monitoria));
    }

    private VagaMonitoria buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Monitoria não encontrada."));
    }

    private void validar(VagaMonitoria dados) {
        if (dados == null
                || dados.getDisciplina() == null
                || dados.getDisciplina().isBlank()
                || dados.getNomeMonitor() == null
                || dados.getNomeMonitor().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Disciplina e nome do monitor são obrigatórios.");
        }

        if (dados.getVagasTotais() == null || dados.getVagasTotais() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O total de vagas deve ser maior que zero.");
        }
    }

    private VagaMonitoriaResponseDTO converter(VagaMonitoria monitoria) {
        return new VagaMonitoriaResponseDTO(
                monitoria.getId(),
                monitoria.getDisciplina(),
                monitoria.getNomeMonitor(),
                monitoria.getVagasTotais(),
                monitoria.getVagasOcupadas(),
                monitoria.getAberta());
    }
}