package com.CasinoLocker.BackEnd.Controllers;

import com.CasinoLocker.BackEnd.Entitys.Reserva;
import com.CasinoLocker.BackEnd.Services.ReservaServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/reservas")
public class ReservaController extends BaseControllerImpl<Reserva, ReservaServiceImpl> {
}
