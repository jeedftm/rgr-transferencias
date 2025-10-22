import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TransferenciaService } from '../../services/transferencia.service';
import { Transferencia } from '../../models/transferencia.model';
import { TransferenciaDTO } from '../../models/transferencia-dto.model';

@Component({
  selector: 'app-transferencias',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './transferencias.component.html',
  styleUrls: ['./transferencias.component.css']
})
export class TransferenciasComponent implements OnInit {

  transferencias: Transferencia[] = [];

  novaTransferencia: TransferenciaDTO = {
    contaOrigem: '',
    contaDestino: '',
    valor: 0,
    dataAgendamento: '',
    dataTransferencia: ''
  };

  constructor(private transferenciaService: TransferenciaService) {}

  ngOnInit(): void {
    this.listarTransferencias();
  }

  listarTransferencias(): void {
    this.transferenciaService.listar().subscribe({
      next: (dados: Transferencia[]) => this.transferencias = dados,
      error: (erro) => console.error('Erro ao listar transferências:', erro)
    });
  }

  agendarTransferencia(): void {
    this.transferenciaService.agendar(this.novaTransferencia).subscribe({
      next: (nova: Transferencia) => {
        alert('Transferência agendada com sucesso!');
        this.transferencias.push(nova);
        this.novaTransferencia = {
          contaOrigem: '',
          contaDestino: '',
          valor: 0,
          dataAgendamento: '',
          dataTransferencia: ''
        };
      },
      error: (erro) => alert(`Erro ao agendar transferência!`)
    });
  }

  deletar(id: number): void {
    this.transferenciaService.deletar(id).subscribe({
      next: () => {
        this.transferencias = this.transferencias.filter(t => t.id !== id);
      },
      error: (erro) => alert('Erro ao deletar transferência!')
    });
  }
}
