package com.CasinoLocker.BackEnd.Controllers;

import com.CasinoLocker.BackEnd.Entitys.Cliente;
import com.CasinoLocker.BackEnd.Services.ClienteServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/clientes")
public class ClienteController extends BaseControllerImpl<Cliente, ClienteServiceImpl> {

}
