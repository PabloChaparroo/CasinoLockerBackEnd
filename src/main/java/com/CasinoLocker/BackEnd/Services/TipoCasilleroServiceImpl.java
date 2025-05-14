package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.Entitys.TipoCasillero;
import com.CasinoLocker.BackEnd.Repositories.BaseRepository;
import com.CasinoLocker.BackEnd.Repositories.TipoCasilleroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoCasilleroServiceImpl extends BaseServiceImpl<TipoCasillero, Long> implements TipoCasilleroService {
    @Autowired
    private TipoCasilleroRepository tipoCasilleroRepository;

    public TipoCasilleroServiceImpl(BaseRepository<TipoCasillero, Long> baseRespository, TipoCasilleroRepository tipoCasilleroRepository) {
        super(baseRespository);
        this.tipoCasilleroRepository = tipoCasilleroRepository;
    }
}
