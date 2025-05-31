package com.CasinoLocker.BackEnd.Services;

import java.util.List;

import com.CasinoLocker.BackEnd.Entitys.Cliente;


public interface ClienteService extends BaseService<Cliente,Long> {

    List<Cliente> buscarPorNombre(String nombre);
    Cliente crearCliente(Cliente cliente) throws Exception;
    Cliente modificarCliente(Long id, Cliente cliente) throws Exception;
    Cliente darDeBajaCliente(Long id) throws Exception;
    Cliente restaurarCliente(Long id) throws Exception;
}
