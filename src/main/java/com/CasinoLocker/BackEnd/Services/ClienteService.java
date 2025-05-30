package com.CasinoLocker.BackEnd.Services;

import java.util.List;

import com.CasinoLocker.BackEnd.Entitys.Cliente;


public interface ClienteService extends BaseService<Cliente,Long> {

List<Cliente> buscarPorNombre(String nombre);
}
