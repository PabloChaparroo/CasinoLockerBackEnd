package com.CasinoLocker.BackEnd.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.CasinoLocker.BackEnd.Entitys.Perfil;
import com.CasinoLocker.BackEnd.Entitys.Usuario;


public interface PerfilRepository extends BaseRepository<Perfil, Long>  {
    Optional<Perfil> findByUsername(String username);

    @Query(
    value = "SELECT * FROM Perfil WHERE username = :username",
    nativeQuery = true
        )
        Perfil findUserByUsername(
            @Param("username") String username
        );

    //Buscar un Perfil con el id de usuario relacionado
    @Query(
            value = "SELECT u.* FROM Perfil u " +
                    "WHERE u.usuario_id = :usuarioId",
            nativeQuery = true
    )
    Perfil findPerfilByUsuarioId(@Param("usuarioId") Long usuarioId);
    
}
