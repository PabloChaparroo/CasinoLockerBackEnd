package com.CasinoLocker.BackEnd.Controllers;

import com.CasinoLocker.BackEnd.Entitys.TipoUsuario;
import com.CasinoLocker.BackEnd.Services.TipoUsuarioServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/tipo_usuarios")
public class TipoUsuarioController extends BaseControllerImpl<TipoUsuario, TipoUsuarioServiceImpl> {
}
