package com.CasinoLocker.BackEnd.Controllers;

import com.CasinoLocker.BackEnd.Entitys.Percha;
import com.CasinoLocker.BackEnd.Services.PerchaServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/perchas")
public class PerchaController extends BaseControllerImpl<Percha, PerchaServiceImpl> {
}
