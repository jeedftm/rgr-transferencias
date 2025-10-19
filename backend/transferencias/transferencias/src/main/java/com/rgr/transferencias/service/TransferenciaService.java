package com.rgr.transferencias.service;

import com.rgr.transferencias.dto.TransferenciaDTO;
import com.rgr.transferencias.model.Transferencia;
import com.rgr.transferencias.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

/**
 * Camada de serviço responsável pela lógica de negócio das transferências financeiras.
 * Aqui são aplicadas as regras de cálculo de taxa, validações e conversões entre DTO e entidade.
 *
 * @author Jedson
 */
@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    /**
     * Lista todas as transferências registradas.
     */
    public List<Transferencia> listarTodas() {
        return transferenciaRepository.findAll();
    }

    /**
     * Busca uma transferência específica por ID.
     */
    public Optional<Transferencia> buscarPorId(Long id) {
        return transferenciaRepository.findById(id);
    }

    /**
     * Cria uma nova transferência com base nas regras de negócio.
     */
    public Transferencia criarTransferencia(TransferenciaDTO dto) {
        validarCampos(dto);

        Transferencia transferencia = new Transferencia();
        transferencia.setContaOrigem(dto.getContaOrigem());
        transferencia.setContaDestino(dto.getContaDestino());
        transferencia.setValor(dto.getValor());
        transferencia.setDataAgendamento(dto.getDataAgendamento());
        transferencia.setDataTransferencia(dto.getDataTransferencia());

        BigDecimal taxa = calcularTaxa(dto.getValor(), dto.getDataAgendamento(), dto.getDataTransferencia());
        transferencia.setTaxa(taxa);

        return transferenciaRepository.save(transferencia);
    }

    /**
     * Agenda uma nova transferência (equivalente ao criarTransferencia).
     */
    public Transferencia agendarTransferencia(TransferenciaDTO dto) {
        return criarTransferencia(dto);
    }

    /**
     * Deleta uma transferência pelo ID, caso exista.
     */
    public boolean deletarTransferencia(Long id) {
        Optional<Transferencia> transferencia = transferenciaRepository.findById(id);
        if (transferencia.isPresent()) {
            transferenciaRepository.delete(transferencia.get());
            return true;
        }
        return false;
    }

    /**
     * Regra de cálculo da taxa conforme o número de dias entre agendamento e transferência.
     */
    private BigDecimal calcularTaxa(BigDecimal valor, LocalDate dataAgendamento, LocalDate dataTransferencia) {
        long dias = ChronoUnit.DAYS.between(dataAgendamento, dataTransferencia);

        if (dias < 0) {
            throw new IllegalArgumentException("A data de transferência não pode ser anterior à data de agendamento.");
        }

        BigDecimal taxa;

        if (dias == 0) {
            taxa = valor.multiply(new BigDecimal("0.025")).add(new BigDecimal("3.00")); // 2,5% + R$3,00
        } else if (dias <= 10) {
            taxa = valor.multiply(new BigDecimal("0.00")).add(new BigDecimal("12.00")); // R$12,00 fixo
        } else if (dias <= 20) {
            taxa = valor.multiply(new BigDecimal("0.082"));
        } else if (dias <= 30) {
            taxa = valor.multiply(new BigDecimal("0.069"));
        } else if (dias <= 40) {
            taxa = valor.multiply(new BigDecimal("0.047"));
        } else if (dias <= 50) {
            taxa = valor.multiply(new BigDecimal("0.017"));
        } else {
            // Caso não haja taxa aplicável
            throw new IllegalArgumentException("Não há taxa aplicável para este intervalo de dias. Operação não permitida.");
        }

        return taxa.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Validação básica dos campos obrigatórios.
     */
    private void validarCampos(TransferenciaDTO dto) {
        if (dto.getContaOrigem() == null || dto.getContaOrigem().isEmpty()) {
            throw new IllegalArgumentException("Conta de origem é obrigatória");
        }
        if (dto.getContaDestino() == null || dto.getContaDestino().isEmpty()) {
            throw new IllegalArgumentException("Conta de destino é obrigatória");
        }
        if (dto.getValor() == null || dto.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor da transferência deve ser maior que zero");
        }
        if (dto.getDataAgendamento() == null || dto.getDataTransferencia() == null) {
            throw new IllegalArgumentException("As datas de agendamento e transferência são obrigatórias");
        }
    }
}
