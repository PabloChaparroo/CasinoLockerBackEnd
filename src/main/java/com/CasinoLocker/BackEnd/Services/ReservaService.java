package com.CasinoLocker.BackEnd.Services;


import com.CasinoLocker.BackEnd.DTO.ReservaClienteReporteDTO;
import com.CasinoLocker.BackEnd.DTO.ReservaDTO;
import com.CasinoLocker.BackEnd.DTO.ReservaReporteDTO;
import com.CasinoLocker.BackEnd.Entitys.Reserva;

import java.time.LocalDateTime;
import java.util.List;


public interface ReservaService extends BaseService<Reserva, Long> {

Reserva createReserva(Reserva reserva) throws Exception;

Reserva findReservaReservadaByIdCasillero(Long idCasillero);

Reserva finalizarReserva(Long idReserva) throws Exception;
List<ReservaDTO> obtenerReservasActivasDTO();
Reserva findReservaReservadaByIdPercha(Long idPercha);
List<ReservaReporteDTO> buscarReservasEntreFechas(LocalDateTime desde, LocalDateTime hasta);
List<ReservaClienteReporteDTO> buscarReservasPorClienteYFechas(Long idCliente, LocalDateTime desde, LocalDateTime hasta);


}
