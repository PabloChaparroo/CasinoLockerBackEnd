package com.CasinoLocker.BackEnd.Repositories;

import com.CasinoLocker.BackEnd.Entitys.Reserva;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends BaseRepository<Reserva,Long> {

     Optional<Reserva> findByNumeroReserva(Integer numeroReserva);
    
    @Query("SELECT MAX(r.numeroReserva) FROM Reserva r")
    Integer findMaxNumeroReserva();

   
    @Query("SELECT r FROM Reserva r WHERE r.casillero.id = :idCasillero AND r.estadoReserva = com.CasinoLocker.BackEnd.Enum.EstadoReserva.Reservado")
    Optional<Reserva> findReservadaByCasilleroId(@Param("idCasillero") Long idCasillero);
}
