package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.Entitys.TipoCasillero;

public interface TipoCasilleroService extends BaseService<TipoCasillero,Long> {
    TipoCasillero darDeBaja(Long id) throws Exception;

    TipoCasillero restaurar(Long id) throws Exception;
}
