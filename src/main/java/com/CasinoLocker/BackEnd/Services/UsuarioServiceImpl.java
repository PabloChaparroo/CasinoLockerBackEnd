package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.DTO.UsuarioDTO;
import com.CasinoLocker.BackEnd.DTO.UsuarioModifyDTO;
import com.CasinoLocker.BackEnd.DTO.UsuarioTablaDTO;
import com.CasinoLocker.BackEnd.Entitys.Casillero;
import com.CasinoLocker.BackEnd.Entitys.Cliente;
import com.CasinoLocker.BackEnd.Entitys.Perfil;
import com.CasinoLocker.BackEnd.Entitys.Usuario;
import com.CasinoLocker.BackEnd.Enum.EstadoUsuario;
import com.CasinoLocker.BackEnd.Enum.Role;
import com.CasinoLocker.BackEnd.Jwt.JwtService;
import com.CasinoLocker.BackEnd.Repositories.BaseRepository;
import com.CasinoLocker.BackEnd.Repositories.PerfilRepository;
import com.CasinoLocker.BackEnd.Repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario,Long> implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private PerfilRepository perfilRepository;


    public UsuarioServiceImpl(BaseRepository<Usuario, Long> baseRespository) {
        super(baseRespository);
        
    }

    
    @Override
    public Usuario searchById(Long idUsuario) throws Exception {
        try {
            Usuario usuario = usuarioRepository.searchById(idUsuario);
            return usuario;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public UsuarioDTO showProfile(String token) throws Exception {
    try {
        String jwtToken = token.substring(7);
        String username = jwtService.getUsernameFromToken(jwtToken);

        // Busca el perfil por username
        Perfil perfil = perfilRepository.findByUsername(username)
            .orElseThrow(() -> new Exception("Perfil no encontrado"));

        Usuario usuario = perfil.getUsuario();

        ModelMapper modelMapper = new ModelMapper();
        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        usuarioDTO.setUsername(username);

        return usuarioDTO;
    } catch (Exception e) {
        throw new Exception(e.getMessage());
    }
}

    @Override
    public UsuarioDTO updateProfile(String token, UsuarioDTO usuarioActualizado) throws Exception {
        try {
            String jwtToken = token.substring(7);
            String username = jwtService.getUsernameFromToken(jwtToken);
            Usuario usuarioExistente = usuarioRepository.findUsuarioByUsername(username);

            //System.out.println("!!!!!!!!nombre UsuarioActualizado " + UsuarioActualizado.getNombre());
            //System.out.println("!!!!!!!!apellido UsuarioActualizado " + UsuarioActualizado.getApellido());

            // Actualizar los campos del Usuario con los nuevos datos
            usuarioExistente.setNombreUsuario(usuarioActualizado.getNombreUsuario());
            usuarioExistente.setDniUsuario(usuarioActualizado.getDniUsuario());
            usuarioExistente.setTelefonoUsuario(usuarioActualizado.getTelefonoUsuario());
            usuarioExistente.setDescripcionUsuario(usuarioActualizado.getDescripcionUsuario());
            usuarioExistente.setEmailUsuario(usuarioActualizado.getEmailUsuario());
            usuarioExistente.setFechaModificacionUsuario(LocalDate.now());

            // Guardar el Usuario actualizado en la base de datos
            Usuario usuarioActualizadoEnBaseDeDatos = usuarioRepository.save(usuarioExistente);

            ModelMapper modelMapper = new ModelMapper();
            UsuarioDTO usuarioDTO = modelMapper.map(usuarioActualizadoEnBaseDeDatos, UsuarioDTO.class);

            return usuarioDTO;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Usuario modifyUsuario(UsuarioModifyDTO usuarioModifyDTO) throws Exception {
        try {
            Usuario usuarioExistente = findById(usuarioModifyDTO.getIdUsuario());
            // 2. Busca el perfil relacionado
            Perfil perfilRelacionado = perfilRepository.findPerfilByUsuarioId(usuarioModifyDTO.getIdUsuario());
            if (perfilRelacionado == null) {
                throw new Exception("Perfil no encontrado para el usuario");
            }

            System.out.println("##################Usuario: "+usuarioExistente.getId()+usuarioExistente.getNombreUsuario());

            usuarioExistente.setNombreUsuario(usuarioModifyDTO.getNombreUsuario());
            usuarioExistente.setDniUsuario(usuarioModifyDTO.getDniUsuario());
            usuarioExistente.setTelefonoUsuario(usuarioModifyDTO.getTelefonoUsuario());
            usuarioExistente.setDescripcionUsuario(usuarioModifyDTO.getDescripcionUsuario());
            usuarioExistente.setEmailUsuario(usuarioModifyDTO.getEmailUsuario());

            // Convertir el int a Role
                Role role = Role.fromValorNumerico(usuarioModifyDTO.getIdRole());
                if (role == null) {
                    throw new Exception("Rol inválido: " + usuarioModifyDTO.getIdRole());
                }
            perfilRelacionado.setRole(role); 
            usuarioExistente.setFechaModificacionUsuario(LocalDate.now());

            /*
            for (DomicilioDTO domicilioDTO : clienteModifyDTO.getDomicilioDTOList()) {
                Domicilio domicilioExistente = domicilioRepository.findById(domicilioDTO.getIdDomicilio()).orElse(null);

                if ((domicilioExistente != null) &&
                        domicilioService.domicilioPerteneceAlCliente(domicilioExistente.getId(), clienteModifyDTO.getIdCliente())) {
                    domicilioExistente.setCalleDomicilio(domicilioDTO.getCalleDomicilio());
                    domicilioExistente.setNroCalleDomicilio(domicilioDTO.getNroCalleDomicilio());
                    domicilioExistente.setDescripcionDomicilio(domicilioDTO.getDescripcionDomicilio());
                    domicilioExistente.setLocalidadDomicilio(domicilioDTO.getLocalidadDomicilio());
                    domicilioExistente.setProvinciaDomicilio(domicilioDTO.getProvinciaDomicilio());




                }
                System.out.println("#############RESULTADO service: "+domicilioService.domicilioPerteneceAlCliente(domicilioExistente.getId(), clienteModifyDTO.getIdCliente()));
            }*/

            return usuarioRepository.save(usuarioExistente);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Usuario deleteUsuario(Long idUsuario) throws Exception {
        try {
            Usuario usuarioExistente = findById(idUsuario);

            usuarioExistente.setFechaBajaUsuario(LocalDate.now());
            usuarioExistente.setEstadoUsuario(EstadoUsuario.USUARIO_INHABILITADO);

            Perfil perfil = perfilRepository.findPerfilByUsuarioId(idUsuario);

            perfil.setFechaBajaPerfil(LocalDate.now());

            return usuarioRepository.save(usuarioExistente);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Usuario getUsuarioByEmailUsuario(String emailUsuario) {
        // Implementación para buscar un Usuario por su correo electrónico en el repositorio
        return usuarioRepository.findByEmailUsuario(emailUsuario);
    }

   @Override
@Transactional
public Usuario restaurarUsuario(Long id) throws Exception {
    Usuario usuarioExistente = findById(id);
    if (usuarioExistente.getFechaBajaUsuario() == null) {
        throw new Exception("usuario no está dado de baja");
    }
    usuarioExistente.setFechaBajaUsuario(null);
    usuarioExistente.setEstadoUsuario(EstadoUsuario.USUARIO_HABILITADO); // Restaurar estado

    Perfil perfil = perfilRepository.findPerfilByUsuarioId(id);
    if (perfil != null) {
        perfil.setFechaBajaPerfil(null); // Restaurar perfil
        perfilRepository.save(perfil);
    }

    return usuarioRepository.save(usuarioExistente);
}

@Override
public List<UsuarioTablaDTO> getUsuariosTabla() throws Exception {
    try {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioTablaDTO> dtos = usuarios.stream().map(usuario -> {
            Perfil perfil = perfilRepository.findPerfilByUsuarioId(usuario.getId());
            Role role = perfil != null ? perfil.getRole() : null; // Usa Role, no String
            return new UsuarioTablaDTO(
                usuario.getId(),
                usuario.getNombreUsuario(),
                usuario.getDniUsuario(),
                usuario.getTelefonoUsuario(),
                usuario.getEmailUsuario(),
                usuario.getDescripcionUsuario(),
                usuario.getFechaAltaUsuario(),
                usuario.getFechaBajaUsuario(),
                usuario.getFechaModificacionUsuario(),
                usuario.getEstadoUsuario(),
                role
            );
        }).toList();
        return dtos;
    } catch (Exception e) {
        throw new Exception("Error al obtener usuarios para la tabla: " + e.getMessage());
    }
}

}
