package com.CasinoLocker.BackEnd.Controllers;

import com.CasinoLocker.BackEnd.Entitys.TipoPerfil;
import com.CasinoLocker.BackEnd.Services.TipoPerfilServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/tipo_perfiles")
public class TipoPerfilController extends BaseControllerImpl<TipoPerfil, TipoPerfilServiceImpl> {
}
