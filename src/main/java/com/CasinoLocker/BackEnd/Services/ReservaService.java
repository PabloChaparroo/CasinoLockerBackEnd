package com.CasinoLocker.BackEnd.Services;


import com.CasinoLocker.BackEnd.DTO.ReservaDTO;
import com.CasinoLocker.BackEnd.Entitys.Reserva;

import java.util.List;


public interface ReservaService extends BaseService<Reserva, Long> {

Reserva createReserva(Reserva reserva) throws Exception;

Reserva findReservaReservadaByIdCasillero(Long idCasillero);

Reserva finalizarReserva(Long idReserva) throws Exception;
List<ReservaDTO> obtenerReservasActivasDTO();

}
