package com.CasinoLocker.BackEnd.Repositories;

import com.CasinoLocker.BackEnd.Entitys.EstadoCasilleroPercha;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstadoCasilleroPerchaRepository extends BaseRepository<EstadoCasilleroPercha,Long> {
    // Buscar solo los que no están dados de baja
    List<EstadoCasilleroPercha> findByFechaBajaEstadoCasilleroPerchaIsNull();
    Optional<EstadoCasilleroPercha> findByNombreEstadoCasilleroPercha(String nombre);
}
