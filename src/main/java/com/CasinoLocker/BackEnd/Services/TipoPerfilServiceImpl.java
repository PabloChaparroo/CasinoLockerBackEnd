package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.Entitys.TipoPerfil;
import com.CasinoLocker.BackEnd.Repositories.BaseRepository;
import com.CasinoLocker.BackEnd.Repositories.TipoPerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoPerfilServiceImpl extends BaseServiceImpl<TipoPerfil,Long> implements TipoPerfilService {
    @Autowired
    private TipoPerfilRepository tipoPerfilRepository;

    public TipoPerfilServiceImpl(BaseRepository<TipoPerfil, Long> baseRespository, TipoPerfilRepository tipoPerfilRepository) {
        super(baseRespository);
        this.tipoPerfilRepository = tipoPerfilRepository;
    }
}
