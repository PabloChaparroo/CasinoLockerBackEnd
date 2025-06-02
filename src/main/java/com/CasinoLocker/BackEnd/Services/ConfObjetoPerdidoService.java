package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.Entitys.Casillero;
import com.CasinoLocker.BackEnd.Entitys.ConfObjetoPerdido;

import java.util.List;

public interface ConfObjetoPerdidoService extends BaseService<ConfObjetoPerdido,Long> {
    ConfObjetoPerdido crearConfObjetoPerdido(Long idReserva, Long idCasillero);
}
