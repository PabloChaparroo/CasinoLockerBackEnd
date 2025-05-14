package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.Entitys.Reserva;
import com.CasinoLocker.BackEnd.Repositories.BaseRepository;
import com.CasinoLocker.BackEnd.Repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServiceImpl extends BaseServiceImpl<Reserva, Long> implements ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    public ReservaServiceImpl(BaseRepository<Reserva, Long> baseRespository, ReservaRepository reservaRepository) {
        super(baseRespository);
        this.reservaRepository = reservaRepository;
    }
}
