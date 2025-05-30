package com.CasinoLocker.BackEnd.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CasinoLocker.BackEnd.Entitys.Perfil;
import com.CasinoLocker.BackEnd.Services.PerfilServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/perfiles")
@RequiredArgsConstructor
public class PerfilController extends BaseControllerImpl<Perfil, PerfilServiceImpl> {


    
}
