import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transferencia } from '../models/transferencia.model';
import { TransferenciaDTO } from '../models/transferencia-dto.model';

@Injectable({ providedIn: 'root' })
export class TransferenciaService {
  private readonly api = 'http://localhost:8080/api/transferencias';

  constructor(private http: HttpClient) {}

  listar(): Observable<Transferencia[]> {
    return this.http.get<Transferencia[]>(this.api);
  }

  agendar(dto: TransferenciaDTO): Observable<Transferencia> {
    return this.http.post<Transferencia>(this.api, dto);
  }

  buscarPorId(id: number): Observable<Transferencia> {
    return this.http.get<Transferencia>(`${this.api}/${id}`);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}
