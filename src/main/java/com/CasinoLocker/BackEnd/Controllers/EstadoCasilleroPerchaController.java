package com.CasinoLocker.BackEnd.Controllers;

import com.CasinoLocker.BackEnd.Entitys.EstadoCasilleroPercha;
import com.CasinoLocker.BackEnd.Services.EstadoCasilleroPerchaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/estado_casillero_percha")
public class EstadoCasilleroPerchaController extends BaseControllerImpl<EstadoCasilleroPercha, EstadoCasilleroPerchaServiceImpl> {

    @Autowired
    private EstadoCasilleroPerchaServiceImpl service;

    @PutMapping("/darDeBaja/{id}")
    public ResponseEntity<?> darDeBaja(@PathVariable Long id) {
        try {
            EstadoCasilleroPercha resultado = service.darDeBaja(id);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/restaurar/{id}")
    public ResponseEntity<?> restaurar(@PathVariable Long id) {
        try {
            EstadoCasilleroPercha resultado = service.restaurar(id);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEstado(@PathVariable Long id, @RequestBody EstadoCasilleroPercha estado) {
        try {
            EstadoCasilleroPercha updatedEstado = service.updateEstado(id, estado);
            return ResponseEntity.ok(updatedEstado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
