package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.Entitys.EstadoCasilleroPercha;

import java.util.List;

public interface EstadoCasilleroPerchaService extends BaseService<EstadoCasilleroPercha, Long> {

    List<EstadoCasilleroPercha> getEstados();

    EstadoCasilleroPercha createEstado(EstadoCasilleroPercha estado) throws Exception;

    EstadoCasilleroPercha updateEstado(Long id, EstadoCasilleroPercha estado) throws Exception;

    EstadoCasilleroPercha darDeBaja(Long id) throws Exception;

    EstadoCasilleroPercha restaurar(Long id) throws Exception;

    void deleteEstado(Long id) throws Exception;
}