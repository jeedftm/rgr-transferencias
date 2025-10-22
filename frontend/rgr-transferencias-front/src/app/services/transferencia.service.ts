import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface TransferenciaService {
  id?: number;
  contaOrigem: string;
  contaDestino: string;
  valor: number;
  taxa?: number;
  dataAgendamento: string;
  dataTransferencia: string;
}

@Injectable({
  providedIn: 'root'
})
export class TransferenciaService {

  private apiUrl = 'http://localhost:8080/api/transferencias';

  constructor(private http: HttpClient) {}

  listarTodas(): Observable<TransferenciaService[]> {
    return this.http.get<TransferenciaService[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<TransferenciaService> {
    return this.http.get<TransferenciaService>(`${this.apiUrl}/${id}`);
  }

  agendar(transferencia: TransferenciaService): Observable<TransferenciaService> {
    return this.http.post<TransferenciaService>(this.apiUrl, transferencia);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
