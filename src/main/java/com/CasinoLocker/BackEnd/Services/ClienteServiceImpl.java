package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.Entitys.Cliente;
import com.CasinoLocker.BackEnd.Repositories.BaseRepository;
import com.CasinoLocker.BackEnd.Repositories.ClienteRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl extends BaseServiceImpl<Cliente, Long> implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteServiceImpl(BaseRepository<Cliente, Long> baseRespository, ClienteRepository clienteRepository) {
        super(baseRespository);
        this.clienteRepository = clienteRepository;
    }

    @Override
public List<Cliente> buscarPorNombre(String nombre) {
    return clienteRepository.buscarPorNombre(nombre);
}

    @Override
    public Cliente crearCliente(Cliente cliente) throws Exception {
        cliente.setId(null);
        cliente.setFechaHoraAltaCliente(LocalDate.now());
        cliente.setFechaHoraModificacionCliente(null);
        cliente.setFechaHoraBajaCliente(null);
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente modificarCliente(Long id, Cliente cliente) throws Exception {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isEmpty()) {
            throw new Exception("Cliente no encontrado");
        }
        Cliente c = clienteExistente.get();
        // Actualizar campos que permitan modificar
        c.setNombreCliente(cliente.getNombreCliente());
        c.setDniCliente(cliente.getDniCliente());
        c.setTelefonoCliente(cliente.getTelefonoCliente());
        c.setMailCliente(cliente.getMailCliente());
        c.setFechaHoraModificacionCliente(LocalDate.now());
        // no tocar fechaHoraAltaCliente ni fechaHoraBajaCliente aquí
        return clienteRepository.save(c);
    }

    @Override
    public Cliente darDeBajaCliente(Long id) throws Exception {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isEmpty()) {
            throw new Exception("Cliente no encontrado");
        }
        Cliente c = clienteExistente.get();
        LocalDate hoy = LocalDate.now();
        c.setFechaHoraBajaCliente(hoy);
        c.setFechaHoraModificacionCliente(hoy);
        return clienteRepository.save(c);
    }

    @Override
    public Cliente restaurarCliente(Long id) throws Exception {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isEmpty()) {
            throw new Exception("Cliente no encontrado");
        }
        Cliente c = clienteExistente.get();
        c.setFechaHoraBajaCliente(null);
        c.setFechaHoraModificacionCliente(LocalDate.now());
        return clienteRepository.save(c);
    }
}

