package com.CasinoLocker.BackEnd.Controllers;

import com.CasinoLocker.BackEnd.DTO.CrearConfObjetoPerdidoDTO;
import com.CasinoLocker.BackEnd.Entitys.Casillero;
import com.CasinoLocker.BackEnd.Entitys.ConfObjetoPerdido;
import com.CasinoLocker.BackEnd.Services.ConfObjetoPerdidoService;
import com.CasinoLocker.BackEnd.Services.ConfObjetoPerdidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/conf_objeto_perdido")
public class ConfObjetoPerdidoController extends BaseControllerImpl<ConfObjetoPerdido, ConfObjetoPerdidoServiceImpl> {
    @Autowired
    private ConfObjetoPerdidoService confObjetoPerdidoService;

    public ConfObjetoPerdidoController(ConfObjetoPerdidoService confObjetoPerdidoService) {
        this.confObjetoPerdidoService = confObjetoPerdidoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<ConfObjetoPerdido> crearConfObjetoPerdido(
            @RequestBody CrearConfObjetoPerdidoDTO request) {
        ConfObjetoPerdido conf = servicio.crearConfObjetoPerdido(request.getIdReserva(), request.getIdCasillero());
        return ResponseEntity.ok(conf);
    }

   }
