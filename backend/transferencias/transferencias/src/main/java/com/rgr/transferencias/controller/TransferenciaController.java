package com.rgr.transferencias.controller;

import com.rgr.transferencias.dto.TransferenciaDTO;
import com.rgr.transferencias.model.Transferencia;
import com.rgr.transferencias.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transferencias")
@CrossOrigin(origins = "*")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @GetMapping
    public ResponseEntity<List<Transferencia>> listarTodas() {
        List<Transferencia> transferencias = transferenciaService.listarTodas();
        return ResponseEntity.ok(transferencias);
    }

    @PostMapping
    public ResponseEntity<Transferencia> agendar(@RequestBody TransferenciaDTO dto) {
        Transferencia nova = transferenciaService.agendarTransferencia(dto);
        return ResponseEntity.ok(nova);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transferencia> buscarPorId(@PathVariable Long id) {
        Optional<Transferencia> transferencia = transferenciaService.buscarPorId(id);

        return transferencia
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean removida = transferenciaService.deletarTransferencia(id);
        if (removida) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
