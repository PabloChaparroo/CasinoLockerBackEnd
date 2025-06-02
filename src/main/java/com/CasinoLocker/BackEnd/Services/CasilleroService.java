package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.Entitys.Casillero;
import com.CasinoLocker.BackEnd.Entitys.EstadoCasilleroPercha;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

public interface CasilleroService extends BaseService<Casillero,Long>{

    List<Casillero> getCasillerosNoBaja();

    Casillero createCasillero(Casillero casillero) throws Exception;
    Casillero updateCasillero(Long id, Casillero casillero) throws Exception;
    Casillero darDeBajaCasillero(Long id) throws Exception;
    Casillero restaurarCasillero(Long id) throws Exception;
    void deleteCasillero(Long id) throws Exception;
    List<Casillero> obtenerCasillerosObjetoPerdido();
}
