package com.CasinoLocker.BackEnd.Repositories;

import com.CasinoLocker.BackEnd.Entitys.Cliente;
import com.CasinoLocker.BackEnd.Entitys.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario,Long> {
    //Buscar un Usuario  con su id
    @Query(
            value = "SELECT * FROM Usuario WHERE id = %:idUsuario%",
            nativeQuery = true
    )
    Usuario searchById(
            @Param("idUsuario") Long idUsuario
    );

    //Buscar un Usuario con su username de Usuario
    @Query(
            value = "SELECT c.* FROM usuario u " +
                    "INNER JOIN usuario c ON u.usuario_id = c.id AND c.estado_usuario = '1'" +
                    "WHERE u.username = :username",
            nativeQuery = true
    )
    Usuario findUsuarioByUsername(@Param("username") String username);

    Usuario findByEmailUsuario(String emailUsuario);
}
