package com.CasinoLocker.BackEnd.Controllers;

import com.CasinoLocker.BackEnd.Entitys.Objeto;
import com.CasinoLocker.BackEnd.Services.ObjetoServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/objetos")
public class ObjetoController extends BaseControllerImpl<Objeto, ObjetoServiceImpl> {
}
