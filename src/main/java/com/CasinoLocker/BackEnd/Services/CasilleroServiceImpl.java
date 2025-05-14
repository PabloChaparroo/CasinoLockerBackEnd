package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.Entitys.Casillero;
import com.CasinoLocker.BackEnd.Repositories.BaseRepository;
import com.CasinoLocker.BackEnd.Repositories.CasilleroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CasilleroServiceImpl extends BaseServiceImpl<Casillero,Long> implements CasilleroService{

    @Autowired
    CasilleroRepository clienteRepository;

    public CasilleroServiceImpl(BaseRepository<Casillero, Long> baseRespository, CasilleroRepository clienteRepository) {
        super(baseRespository);
        this.clienteRepository = clienteRepository;
    }
}
