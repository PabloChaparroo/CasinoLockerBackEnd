package com.CasinoLocker.BackEnd.Repositories;

import com.CasinoLocker.BackEnd.Entitys.Reserva;

import java.util.List;
import java.util.Optional;

import com.CasinoLocker.BackEnd.Enum.EstadoReserva;
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
    List<Reserva> findByEstadoReserva(EstadoReserva estado);
    @Query("SELECT r FROM Reserva r WHERE r.percha.id = :idPercha AND r.estadoReserva = com.CasinoLocker.BackEnd.Enum.EstadoReserva.Reservado")
    Optional<Reserva> findReservadaByPerchaId(@Param("idPercha") Long idPercha);

    @Query("SELECT r FROM Reserva r WHERE r.fechaAltaReserva >= :desde AND r.fechaAltaReserva <= :hasta")
    List<Reserva> findByFechaAltaReservaBetween(@Param("desde") java.time.LocalDateTime desde, @Param("hasta") java.time.LocalDateTime hasta);

    @Query("SELECT r FROM Reserva r WHERE r.cliente.id = :idCliente AND r.fechaAltaReserva >= :desde AND r.fechaAltaReserva <= :hasta")
    List<Reserva> findByClienteIdAndFechaAltaReservaBetween(@Param("idCliente") Long idCliente, @Param("desde") java.time.LocalDateTime desde, @Param("hasta") java.time.LocalDateTime hasta);
}
