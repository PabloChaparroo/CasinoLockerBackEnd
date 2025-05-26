package com.CasinoLocker.BackEnd.Controllers;

import com.CasinoLocker.BackEnd.Entitys.Casillero;
import com.CasinoLocker.BackEnd.Entitys.EstadoCasilleroPercha;
import com.CasinoLocker.BackEnd.Services.CasilleroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/casilleros")
public class CasilleroController extends BaseControllerImpl<Casillero, CasilleroServiceImpl> {
    @Autowired
    private CasilleroServiceImpl service;

    @PostMapping("create")
    public ResponseEntity<?> save(@RequestBody Casillero casillero){
        try {
            casillero.setId(null);
            return ResponseEntity.status(HttpStatus.OK).body(servicio.createCasillero(casillero));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" Error\"}");
        }
    }

    @PutMapping("/darDeBajaCasillero/{id}")
    public ResponseEntity<?> darDeBajaCasillero(@PathVariable Long id) {
        try {
            Casillero resultado = service.darDeBajaCasillero(id);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/restaurar/{id}")
    public ResponseEntity<?> restaurarCasillero(@PathVariable Long id) {
        try {
            Casillero resultado = service.restaurarCasillero(id);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCasillero(@PathVariable Long id, @RequestBody Casillero casillero) {
        try {
            Casillero updatedCasillero = service.updateCasillero(id, casillero);
            return ResponseEntity.ok(updatedCasillero);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
