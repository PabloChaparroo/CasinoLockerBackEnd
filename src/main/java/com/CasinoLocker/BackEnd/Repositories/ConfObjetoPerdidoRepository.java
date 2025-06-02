package com.CasinoLocker.BackEnd.Repositories;

import com.CasinoLocker.BackEnd.Entitys.ConfObjetoPerdido;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfObjetoPerdidoRepository extends BaseRepository<ConfObjetoPerdido,Long> {
    Optional<ConfObjetoPerdido> findByCasilleroId(Long casilleroId);
}
