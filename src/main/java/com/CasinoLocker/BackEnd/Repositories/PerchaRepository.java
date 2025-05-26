package com.CasinoLocker.BackEnd.Repositories;

import com.CasinoLocker.BackEnd.Entitys.Percha;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PerchaRepository extends BaseRepository<Percha,Long>{
    List<Percha> findByFechaBajaPerchaIsNull();
}
