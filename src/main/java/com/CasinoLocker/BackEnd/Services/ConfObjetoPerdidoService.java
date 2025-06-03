package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.Entitys.Casillero;
import com.CasinoLocker.BackEnd.Entitys.ConfObjetoPerdido;
import com.CasinoLocker.BackEnd.DTO.ConfObjetoPerdidoDetalleDTO;
import com.CasinoLocker.BackEnd.Enum.EstadoReserva;

import java.util.List;

public interface ConfObjetoPerdidoService extends BaseService<ConfObjetoPerdido,Long> {
    ConfObjetoPerdido crearConfObjetoPerdido(Long idReserva, Long idCasillero);
    ConfObjetoPerdidoDetalleDTO obtenerDetallePorIdCasillero(Long idCasillero);
    void despacharReserva(Long idReserva);
}
