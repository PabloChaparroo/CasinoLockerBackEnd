package com.CasinoLocker.BackEnd.Repositories;

import com.CasinoLocker.BackEnd.Entitys.Casillero;
import com.CasinoLocker.BackEnd.Entitys.EstadoCasilleroPercha;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CasilleroRepository extends BaseRepository<Casillero,Long> {
    // Buscar solo los que no están dados de baja
    List<Casillero> findByFechaBajaCasilleroIsNull();
    // CasilleroRepository.java
    Optional<Casillero> findByNumeroCasillero(Integer numeroCasillero);
    @Query("SELECT c FROM Casillero c WHERE c.tipoCasillero.nombreTipoCasillero = 'Objeto_Perdido'")
    List<Casillero> findCasillerosDeObjetosPerdidos();;
}
