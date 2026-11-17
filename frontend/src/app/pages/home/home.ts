import { Component, inject, OnInit, signal } from '@angular/core';
import { MonitoriaService } from '../../core/services/monitoria-service';
import { VagaMonitoria } from '../../core/model/vaga-monitoria';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home implements OnInit {
  monitorias = signal<VagaMonitoria[]>([]);
  carregando = signal(false);
  erro = signal('');

  private service = inject(MonitoriaService);

  ngOnInit(): void {
    this.carregar();
  }

  carregar(): void {
    this.carregando.set(true);
    this.erro.set('');

    this.service.listar().subscribe({
      next: (dados) => {
        this.monitorias.set(dados);
        this.carregando.set(false);
      },
      error: () => {
        this.erro.set('Não foi possível carregar. Verifique o backend.');
        this.carregando.set(false);
      }
    });
  }

  inscrever(id: number): void {
    this.service.inscreverAluno(id).subscribe({
      next: () => this.carregar(),
      error: () => alert('Não foi possível realizar a inscrição.')
    });
  }

  excluir(id: number): void {
    if (!confirm('Deseja excluir esta monitoria?')) {
      return;
    }

    this.service.excluir(id).subscribe({
      next: () => this.carregar(),
      error: () => alert('Não foi possível excluir a monitoria.')
    });
  }
}
