package com.CasinoLocker.BackEnd.Repositories;

import com.CasinoLocker.BackEnd.Entitys.Cliente;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente,Long>{

    @Query("SELECT c FROM Cliente c WHERE UPPER(c.nombreCliente) LIKE CONCAT('%', UPPER(:nombre), '%')")
    List<Cliente> buscarPorNombre(@Param("nombre") String nombre);
}
