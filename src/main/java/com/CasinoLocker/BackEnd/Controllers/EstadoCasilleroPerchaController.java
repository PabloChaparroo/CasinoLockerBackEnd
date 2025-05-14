package com.CasinoLocker.BackEnd.Controllers;

import com.CasinoLocker.BackEnd.Entitys.EstadoCasilleroPercha;
import com.CasinoLocker.BackEnd.Services.EstadoCasilleroPerchaServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/estado_casillero_percha")
public class EstadoCasilleroPerchaController extends BaseControllerImpl<EstadoCasilleroPercha, EstadoCasilleroPerchaServiceImpl> {
}
