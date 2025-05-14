package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.Entitys.ConfObjetoPerdido;
import com.CasinoLocker.BackEnd.Repositories.BaseRepository;
import com.CasinoLocker.BackEnd.Repositories.ConfObjetoPerdidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfObjetoPerdidoServiceImpl extends BaseServiceImpl<ConfObjetoPerdido,Long> implements ConfObjetoPerdidoService {

    @Autowired
    private ConfObjetoPerdidoRepository confObjetoPerdidoRepository;

    public ConfObjetoPerdidoServiceImpl(BaseRepository<ConfObjetoPerdido, Long> baseRespository, ConfObjetoPerdidoRepository confObjetoPerdidoRepository) {
        super(baseRespository);
        this.confObjetoPerdidoRepository = confObjetoPerdidoRepository;
    }
}
