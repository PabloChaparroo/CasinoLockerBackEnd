package com.CasinoLocker.BackEnd.Controllers;

import com.CasinoLocker.BackEnd.Entitys.TipoCasillero;
import com.CasinoLocker.BackEnd.Services.TipoCasilleroServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/tipo_casilleros")
public class TipoCasilleroController extends BaseControllerImpl<TipoCasillero, TipoCasilleroServiceImpl> {
}
