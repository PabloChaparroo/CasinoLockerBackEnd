package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.Entitys.TipoUsuario;
import com.CasinoLocker.BackEnd.Repositories.BaseRepository;
import com.CasinoLocker.BackEnd.Repositories.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoUsuarioServiceImpl extends BaseServiceImpl<TipoUsuario,Long> implements TipoUsuarioService {
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    public TipoUsuarioServiceImpl(BaseRepository<TipoUsuario, Long> baseRespository, TipoUsuarioRepository tipoUsuarioRepository) {
        super(baseRespository);
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }
}
