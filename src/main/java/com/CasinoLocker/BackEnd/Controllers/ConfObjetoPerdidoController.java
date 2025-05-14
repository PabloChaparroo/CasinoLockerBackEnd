package com.CasinoLocker.BackEnd.Controllers;

import com.CasinoLocker.BackEnd.Entitys.ConfObjetoPerdido;
import com.CasinoLocker.BackEnd.Services.ConfObjetoPerdidoServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/conf_objeto_perdido")
public class ConfObjetoPerdidoController extends BaseControllerImpl<ConfObjetoPerdido, ConfObjetoPerdidoServiceImpl> {
}
