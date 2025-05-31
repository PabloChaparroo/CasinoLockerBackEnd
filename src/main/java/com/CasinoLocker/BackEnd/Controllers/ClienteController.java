package com.CasinoLocker.BackEnd.Controllers;

import com.CasinoLocker.BackEnd.Entitys.Cliente;
import com.CasinoLocker.BackEnd.Services.ClienteServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/clientes")
public class ClienteController extends BaseControllerImpl<Cliente, ClienteServiceImpl> {


    @Autowired
    private ClienteServiceImpl servicio;

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<Cliente>> buscarPorNombre(@PathVariable String nombre){
        try {
            
        
        List<Cliente> clientes = servicio.buscarPorNombre(nombre);
        return ResponseEntity.ok(clientes);
           
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    // Crear cliente (con fecha de alta automática)
    @PostMapping("/crear")
    public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente){
        try {
            cliente.setId(null);
            Cliente nuevoCliente = servicio.crearCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    // Modificar cliente (con fecha de modificación automática)
    @PutMapping("/modificar/{id}")
    public ResponseEntity<?> modificarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        try {
            Cliente actualizado = servicio.modificarCliente(id, cliente);
            return ResponseEntity.status(HttpStatus.OK).body(actualizado);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    // Dar de baja cliente (setea fechaHoraBajaCliente y fechaHoraModificacionCliente)
    @PutMapping("/darDeBaja/{id}")
    public ResponseEntity<?> darDeBajaCliente(@PathVariable Long id){
        try {
            Cliente clienteBaja = servicio.darDeBajaCliente(id);
            return ResponseEntity.status(HttpStatus.OK).body(clienteBaja);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    // Restaurar cliente (elimina fechaHoraBajaCliente y actualiza fechaHoraModificacionCliente)
    @PutMapping("/restaurar/{id}")
    public ResponseEntity<?> restaurarCliente(@PathVariable Long id){
        try {
            Cliente clienteRestaurado = servicio.restaurarCliente(id);
            return ResponseEntity.status(HttpStatus.OK).body(clienteRestaurado);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

}
