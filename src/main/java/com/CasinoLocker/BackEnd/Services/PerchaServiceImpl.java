package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.Entitys.Percha;
import com.CasinoLocker.BackEnd.Repositories.BaseRepository;
import com.CasinoLocker.BackEnd.Repositories.PerchaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerchaServiceImpl extends BaseServiceImpl<Percha, Long> implements PerchaService {

    @Autowired
    private PerchaRepository perchaRepository;

    public PerchaServiceImpl(BaseRepository<Percha, Long> baseRespository, PerchaRepository perchaRepository) {
        super(baseRespository);
        this.perchaRepository = perchaRepository;
    }
}
