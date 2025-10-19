package com.rgr.transferencias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rgr.transferencias.model.Transferencia;

/**
 * Repositório responsável por acessar os dados de Transferencia no banco.
 * Usa o JpaRepository, que já oferece métodos prontos como:
 * - findAll()
 * - findById()
 * - save()
 * - deleteById()
 *
 * @author Jedson
 */

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    // Ajustes personalizados caso necessário..

}
