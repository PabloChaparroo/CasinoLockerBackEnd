package com.CasinoLocker.BackEnd.Controllers;

import com.CasinoLocker.BackEnd.Entitys.Restriccion;

import com.CasinoLocker.BackEnd.Services.RestriccionServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/restricciones")
public class ResticcionController extends BaseControllerImpl<Restriccion, RestriccionServiceImpl> {
}
