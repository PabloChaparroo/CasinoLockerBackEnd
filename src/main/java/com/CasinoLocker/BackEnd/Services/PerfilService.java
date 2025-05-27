package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.Entitys.Perfil;

public interface PerfilService extends BaseService<Perfil, Long> {

    Perfil findUserByUsername(String username) throws Exception;
}
