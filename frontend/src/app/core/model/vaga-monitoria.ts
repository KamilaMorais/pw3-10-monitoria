export interface VagaMonitoria {
  id: number;
  disciplina: string;
  nomeMonitor: string;
  vagasTotais: number;
  vagasOcupadas: number;
  aberta: boolean;
}

export interface VagaMonitoriaCadastro {
  disciplina: string;
  nomeMonitor: string;
  vagasTotais: number;
}
