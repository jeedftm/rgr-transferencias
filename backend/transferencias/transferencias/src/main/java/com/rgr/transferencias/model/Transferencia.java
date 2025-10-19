package com.rgr.transferencias.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transferencias")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contaOrigem;

    @Column(nullable = false)
    private String contaDestino;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private BigDecimal taxa;

    @Column(nullable = false)
    private LocalDate dataAgendamento;

    @Column(nullable = false)
    private LocalDate dataTransferencia;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContaOrigem() { return contaOrigem; }
    public void setContaOrigem(String contaOrigem) { this.contaOrigem = contaOrigem; }

    public String getContaDestino() { return contaDestino; }
    public void setContaDestino(String contaDestino) { this.contaDestino = contaDestino; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public BigDecimal getTaxa() { return taxa; }
    public void setTaxa(BigDecimal taxa) { this.taxa = taxa; }

    public LocalDate getDataAgendamento() { return dataAgendamento; }
    public void setDataAgendamento(LocalDate dataAgendamento) { this.dataAgendamento = dataAgendamento; }

    public LocalDate getDataTransferencia() { return dataTransferencia; }
    public void setDataTransferencia(LocalDate dataTransferencia) { this.dataTransferencia = dataTransferencia; }
}
