package com.CasinoLocker.BackEnd.Controllers;

import com.CasinoLocker.BackEnd.Entitys.Percha;
import com.CasinoLocker.BackEnd.Services.PerchaService;
import com.CasinoLocker.BackEnd.Services.PerchaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/perchas")
@RequiredArgsConstructor
public class PerchaController {

    private final PerchaService perchaService;

    @GetMapping
    public List<Percha> getAll() {
        return perchaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Percha> getById(@PathVariable Long id) {
        return ResponseEntity.ok(perchaService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Percha> create(@RequestBody Percha percha) {
        return ResponseEntity.ok(perchaService.create(percha));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Percha> update(@PathVariable Long id, @RequestBody Percha percha) {
        return ResponseEntity.ok(perchaService.update(id, percha));
    }

    @PutMapping("/{id}/baja")
    public ResponseEntity<Percha> darDeBaja(@PathVariable Long id) {
        return ResponseEntity.ok(perchaService.darDeBaja(id));
    }

    @PutMapping("/{id}/alta")
    public ResponseEntity<Percha> darDeAlta(@PathVariable Long id) {
        return ResponseEntity.ok(perchaService.darDeAlta(id));
    }
}
