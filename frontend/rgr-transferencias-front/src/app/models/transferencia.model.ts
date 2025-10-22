export interface Transferencia {
  id: number;
  contaOrigem: string;
  contaDestino: string;
  valor: number;
  taxa: number;
  dataAgendamento: string;    // Tanto faz ser date
  dataTransferencia: string;  // idem
}
