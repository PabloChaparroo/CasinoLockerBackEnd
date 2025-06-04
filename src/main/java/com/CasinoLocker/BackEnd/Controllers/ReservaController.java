package com.CasinoLocker.BackEnd.Controllers;

import com.CasinoLocker.BackEnd.Entitys.Casillero;
import com.CasinoLocker.BackEnd.Entitys.Reserva;
import com.CasinoLocker.BackEnd.Services.CasilleroServiceImpl;
import com.CasinoLocker.BackEnd.Services.ReservaServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/reservas")
public class ReservaController extends BaseControllerImpl<Reserva, ReservaServiceImpl> {


@Autowired
private ReservaServiceImpl service;

    @PostMapping("create")
    public ResponseEntity<?> save(@RequestBody Reserva reserva){
        try {
            reserva.setId(null);
            return ResponseEntity.status(HttpStatus.OK).body(servicio.createReserva(reserva));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" Error\"}");
        }
    }

    @GetMapping("reservaPorCasillero/{idCasillero}")
public ResponseEntity<?> getReservaByCasillero(@PathVariable Long idCasillero) {
    try {
      
        Object reserva = service.findReservaReservadaByIdCasillero(idCasillero);
        if (reserva != null) {
            return ResponseEntity.status(HttpStatus.OK).body(reserva);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Reserva no encontrada\"}");
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Error al buscar la reserva\"}");
    }
}

@PutMapping("finalizar/{idReserva}")
    public ResponseEntity<?> finalizarReserva(@PathVariable Long idReserva) {
        try {
            Reserva reservaFinalizada = service.finalizarReserva(idReserva);
            return ResponseEntity.status(HttpStatus.OK).body(reservaFinalizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error al finalizar la reserva\"}");
        }
    }
    @GetMapping("/activas")
    public ResponseEntity<?> getReservasActivas() {
        try {
            return ResponseEntity.ok(service.obtenerReservasActivasDTO());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Error al obtener reservas activas\"}");
        }
    }
    @GetMapping("reservaPorPercha/{idPercha}")
    public ResponseEntity<?> getReservaByPercha(@PathVariable Long idPercha) {
        try {
            Reserva reserva = service.findReservaReservadaByIdPercha(idPercha);
            return ResponseEntity.status(HttpStatus.OK).body(reserva);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Reserva no encontrada\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Error al buscar la reserva\"}");
        }
    }
    @GetMapping("/reporte")
    public ResponseEntity<?> getReservasEntreFechas(
            @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) java.time.LocalDateTime desde,
            @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) java.time.LocalDateTime hasta) {
        try {
            return ResponseEntity.ok(service.buscarReservasEntreFechas(desde, hasta));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Error al obtener reservas por fechas\"}");
        }
    }
    @PutMapping("/saveAll")
    public ResponseEntity<?> saveAll(@RequestBody java.util.List<Reserva> reservas) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.saveAll(reservas));
        } catch (Exception e) {
            throw new Exception("Error al guardar los datos: " + e.getMessage());
        }
    }
    @GetMapping("/reportePorCliente")
    public ResponseEntity<?> getReservasPorClienteYFechas(
            @RequestParam("idCliente") Long idCliente,
            @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) java.time.LocalDateTime desde,
            @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) java.time.LocalDateTime hasta) {
        try {
            return ResponseEntity.ok(service.buscarReservasPorClienteYFechas(idCliente, desde, hasta));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Error al obtener reservas por cliente y fechas\"}");
        }
    }

}
