package com.CasinoLocker.BackEnd.Repositories;

import com.CasinoLocker.BackEnd.Entitys.TipoCasillero;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoCasilleroRepository extends BaseRepository<TipoCasillero,Long> {
    List<TipoCasillero> findAllByFechaBajaTipoCasilleroIsNull();

}
