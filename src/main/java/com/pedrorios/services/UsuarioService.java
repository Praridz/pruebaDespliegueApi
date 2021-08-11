package com.pedrorios.services;

import com.pedrorios.constants.EstadosConstants;
import com.pedrorios.dtos.LoginDTO;
import com.pedrorios.dtos.UsuarioDTO;
import com.pedrorios.mappers.UsuarioDTOMapper;
import com.pedrorios.models.entities.tables.Usuario;
import com.pedrorios.models.responses.ResponseMessageCodeObject;
import com.pedrorios.repositories.UsuarioRepository;
import com.pedrorios.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UsuarioService {
    private static Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDTO> obtenerUsuarios(){
        logger.info("/obtener usuarios is called");
        return usuarioRepository.findAll().stream()
                .map(UsuarioDTOMapper::mapearUsuario)
                .collect(Collectors.toList());
    }

    public UsuarioDTO crearPaciente(UsuarioDTO usuarioDTO) {
        logger.info("/crearPaciente is called with the body {} ", usuarioDTO);
        try {
            usuarioRepository.save(Usuario.builder()
                    .nombre(usuarioDTO.getNombre())
                    .apellido(usuarioDTO.getApellido())
                    .correo(usuarioDTO.getCorreo())
                    .password(usuarioDTO.getPassword())
                    .telefono(usuarioDTO.getTelefono())
                    .direccion(usuarioDTO.getDireccion())
                    .fechaCreacion(new Date())
                    .fechaUltimoIngreso(new Date())
                    .rol(EstadosConstants.PACIENTE)
                    .build());
            logger.info("-El paciente se creó correctamente");
        } catch (Exception e) {
            return null;
        }
        return usuarioDTO;
    }

    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO,String id) {
        logger.info("/actualizarUsuario is called with the id: "+id+" and body {} ", usuarioDTO);
        try {
            Usuario usuario = usuarioRepository.save(Usuario.builder()
                    .id(Integer.parseInt(id))
                    .nombre(usuarioDTO.getNombre())
                    .apellido(usuarioDTO.getApellido())
                    .correo(usuarioDTO.getCorreo())
                    .password(usuarioDTO.getPassword())
                    .telefono(usuarioDTO.getTelefono())
                    .direccion(usuarioDTO.getDireccion())
                    .fechaCreacion(DateUtils.df.parse(usuarioDTO.getFechaCreacion()))
                    .fechaUltimoIngreso(DateUtils.df.parse(usuarioDTO.getFechaUltimoIngreso()))
                    .rol(usuarioDTO.getRol())
                    .build());
            logger.info("-El usuario se actualizó correctamente");
        } catch (Exception e) {
            return null;
        }
        return usuarioDTO;
    }

    public int eliminarUsuario(String id) {
        logger.info("/eliminarUsuario is called with the path {} ", id);

        try {
                usuarioRepository.deleteById(Integer.parseInt(id));
                logger.info("-El usuario se eliminó correctamente");
                return 200;
            } catch (Exception e) {
                logger.error("No se pudo eliminar el usuario con id " + id);
                return 404;
            }
    }

    public ResponseMessageCodeObject loguearse(LoginDTO loginDTO) {
        logger.info("/loguearse is called with the body {} ", loginDTO);
        Integer responseCode = 401;
        String message = "Credenciales no correctas";
        Object object = null;
        try {
            Usuario usuario =  usuarioRepository.findByCorreoAndPassword(loginDTO.getCorreo(),loginDTO.getPassword());;

            if(usuario != null){
                responseCode = 200;
                message = "Logueo Exitoso";
                usuario.setFechaUltimoIngreso(new Date());
                usuarioRepository.save(usuario);
                usuario.setPassword("***");
                object=usuario;
                logger.info("-El usuario se logueó correctamente");
            }

        } catch (Exception e) {
            logger.error("No fue posible el logueo");
            object = "Falló";

        }
        return ResponseMessageCodeObject.builder()
                .code(responseCode)
                .message(message)
                .object(object)
                .build();
    }

}
