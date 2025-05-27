package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.DTO.UsuarioDTO;
import com.CasinoLocker.BackEnd.DTO.UsuarioModifyDTO;
import com.CasinoLocker.BackEnd.Entitys.Cliente;
import com.CasinoLocker.BackEnd.Entitys.Usuario;

public interface UsuarioService extends BaseService<Usuario,Long>{
    Usuario searchById(Long idUsuario) throws Exception;

    UsuarioDTO showProfile(String token) throws Exception;

    UsuarioDTO updateProfile(String token, UsuarioDTO usuarioActualizado) throws Exception;

    Usuario modifyUsuario(UsuarioModifyDTO usuarioModifyDTO) throws Exception;

    Usuario deleteUsuario(Long idUsuario) throws Exception;

    Usuario getUsuarioByEmailUsuario(String emailUsuario) throws Exception;


}
