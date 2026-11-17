import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {
  VagaMonitoria,
  VagaMonitoriaCadastro
} from '../model/vaga-monitoria';

@Injectable({
  providedIn: 'root'
})
export class MonitoriaService {
  private apiUrl = '/api/monitorias';
  private http = inject(HttpClient);

  listar(): Observable<VagaMonitoria[]> {
    return this.http.get<VagaMonitoria[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<VagaMonitoria> {
    return this.http.get<VagaMonitoria>(`${this.apiUrl}/${id}`);
  }

  cadastrar(dados: VagaMonitoriaCadastro): Observable<VagaMonitoria> {
    return this.http.post<VagaMonitoria>(this.apiUrl, dados);
  }

  atualizar(
    id: number,
    dados: VagaMonitoriaCadastro
  ): Observable<VagaMonitoria> {
    return this.http.put<VagaMonitoria>(`${this.apiUrl}/${id}`, dados);
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  inscreverAluno(id: number): Observable<VagaMonitoria> {
    return this.http.post<VagaMonitoria>(
      `${this.apiUrl}/${id}/inscrever`,
      {}
    );
  }
}
