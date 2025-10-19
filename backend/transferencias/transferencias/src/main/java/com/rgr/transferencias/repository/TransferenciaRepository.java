package com.rgr.transferencias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rgr.transferencias.model.Transferencia;

/**
 * Repositório responsável pelo acesso e persistência de dados da entidade Transferencia.
 * Estende JpaRepository para herdar métodos CRUD (save, findAll, findById, delete, etc).
 *
 * @author Jedson
 */

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    // Ajustes personalizados caso necessário..

}
