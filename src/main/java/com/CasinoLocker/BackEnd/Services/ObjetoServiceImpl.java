package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.Entitys.Objeto;
import com.CasinoLocker.BackEnd.Repositories.BaseRepository;
import com.CasinoLocker.BackEnd.Repositories.ObjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjetoServiceImpl extends BaseServiceImpl<Objeto, Long> implements ObjetoService {
    @Autowired
    private ObjetoRepository objetoRepository;

    public ObjetoServiceImpl(BaseRepository<Objeto, Long> baseRespository, ObjetoRepository objetoRepository) {
        super(baseRespository);
        this.objetoRepository = objetoRepository;
    }
}
