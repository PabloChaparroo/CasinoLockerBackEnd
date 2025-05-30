package com.CasinoLocker.BackEnd.Services;


import com.CasinoLocker.BackEnd.Entitys.Reserva;


public interface ReservaService extends BaseService<Reserva, Long> {

Reserva createReserva(Reserva reserva) throws Exception;

Reserva findReservaReservadaByIdCasillero(Long idCasillero);

Reserva finalizarReserva(Long idReserva) throws Exception;

}
