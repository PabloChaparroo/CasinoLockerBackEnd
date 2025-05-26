package com.CasinoLocker.BackEnd.Controllers;

import com.CasinoLocker.BackEnd.Entitys.TipoCasillero;
import com.CasinoLocker.BackEnd.Repositories.TipoCasilleroRepository;
import com.CasinoLocker.BackEnd.Services.TipoCasilleroService;
import com.CasinoLocker.BackEnd.Services.TipoCasilleroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/tipo_casilleros")
public class TipoCasilleroController extends BaseControllerImpl<TipoCasillero, TipoCasilleroServiceImpl> {
    @Autowired
    private TipoCasilleroService tipoCasilleroService;
    @Autowired
    private TipoCasilleroRepository tipoCasilleroRepository;
    @PutMapping("/baja/{id}")
    public ResponseEntity<TipoCasillero> darDeBaja(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(tipoCasilleroService.darDeBaja(id));
    }
    @PutMapping("/restaurar/{id}")
    public ResponseEntity<TipoCasillero> restaurar(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(tipoCasilleroService.restaurar(id));
    }

}
