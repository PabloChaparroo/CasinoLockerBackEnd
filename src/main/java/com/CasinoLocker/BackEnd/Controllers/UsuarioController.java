package com.CasinoLocker.BackEnd.Controllers;

import com.CasinoLocker.BackEnd.DTO.UsuarioDTO;
import com.CasinoLocker.BackEnd.DTO.UsuarioModifyDTO;
import com.CasinoLocker.BackEnd.Entitys.Usuario;
import com.CasinoLocker.BackEnd.Services.UsuarioServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/usuarios")
public class UsuarioController extends BaseControllerImpl<Usuario, UsuarioServiceImpl> {

 @GetMapping("/searchById")
    public ResponseEntity<?> searchById(Long idUsuario) {
        try {
            Usuario usuario = servicio.searchById(idUsuario);
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/showProfile")
    public ResponseEntity<?> showProfile(@RequestHeader(name = "Authorization") String token) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.showProfile(token));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<?> updateProfile(@RequestHeader(name = "Authorization") String token, @RequestBody UsuarioDTO usuarioActualizado) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.updateProfile(token, usuarioActualizado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Los datos de la solicitud son inválidos. Por favor, revise los datos e intente de nuevo.\"}");
        }
    }

    @PutMapping("/modifyUsuario")
    public ResponseEntity<?> modifyUsuario(@RequestBody UsuarioModifyDTO usuarioModifyDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.modifyUsuario(usuarioModifyDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Los datos de la solicitud son inválidos. Por favor, revise los datos e intente de nuevo.\"}");
        }
    }

    @PutMapping("/deleteUsuario")
    public ResponseEntity<?> deleteUsuario(@RequestBody Long idUsuario) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.deleteUsuario(idUsuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Los datos de la solicitud son inválidos. Por favor, revise los datos e intente de nuevo.\"}");
        }
    }

    @GetMapping("/byMailUsuario")
    public ResponseEntity<?> getUsuarioByMailUsuario(@RequestParam String emailUsuario) {
        try {
            Usuario usuario = servicio.getUsuarioByEmailUsuario(emailUsuario);
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

}
