package com.CasinoLocker.BackEnd.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CasinoLocker.BackEnd.Entitys.Perfil;
import com.CasinoLocker.BackEnd.Entitys.Usuario;
import com.CasinoLocker.BackEnd.Repositories.PerfilRepository;

@Service
public class PerfilServiceImpl extends BaseServiceImpl<Perfil,Long> implements PerfilService{
   @Autowired
   private PerfilRepository perfilRepository;
   
   public PerfilServiceImpl(PerfilRepository perfilRepository) {
       super(perfilRepository);
       this.perfilRepository = perfilRepository;
   }

   @Override
    public Perfil findUserByUsername(String username) throws Exception{
        try {
            Perfil perfil = perfilRepository.findUserByUsername(username);
            return perfil;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}
