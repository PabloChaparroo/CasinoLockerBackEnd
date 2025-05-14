package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.Entitys.Restriccion;
import com.CasinoLocker.BackEnd.Repositories.BaseRepository;
import com.CasinoLocker.BackEnd.Repositories.RestriccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestriccionServiceImpl extends BaseServiceImpl<Restriccion, Long> implements RestriccionService {
    @Autowired
    private RestriccionRepository restriccionRepository;

    public RestriccionServiceImpl(BaseRepository<Restriccion, Long> baseRespository, RestriccionRepository restriccionRepository) {
        super(baseRespository);
        this.restriccionRepository = restriccionRepository;
    }
}
