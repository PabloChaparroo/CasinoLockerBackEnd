package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.Entitys.EstadoCasilleroPercha;
import com.CasinoLocker.BackEnd.Repositories.BaseRepository;
import com.CasinoLocker.BackEnd.Repositories.EstadoCasilleroPerchaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoCasilleroPerchaServiceImpl extends BaseServiceImpl<EstadoCasilleroPercha,Long> implements EstadoCasilleroPerchaService {
    @Autowired
    private EstadoCasilleroPerchaRepository estadoCasilleroPerchaRepository;

    public EstadoCasilleroPerchaServiceImpl(BaseRepository<EstadoCasilleroPercha, Long> baseRespository, EstadoCasilleroPerchaRepository estadoCasilleroPerchaRepository) {
        super(baseRespository);
        this.estadoCasilleroPerchaRepository = estadoCasilleroPerchaRepository;
    }
}
