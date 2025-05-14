package com.CasinoLocker.BackEnd.Controllers;

import com.CasinoLocker.BackEnd.Entitys.Casillero;
import com.CasinoLocker.BackEnd.Services.CasilleroServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/casilleros")
public class CasilleroController extends BaseControllerImpl<Casillero, CasilleroServiceImpl> {
}
