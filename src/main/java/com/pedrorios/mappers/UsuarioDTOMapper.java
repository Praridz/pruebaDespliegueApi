package com.pedrorios.mappers;

import com.pedrorios.dtos.UsuarioDTO;
import com.pedrorios.models.entities.tables.Usuario;
import com.pedrorios.utils.DateUtils;

public class UsuarioDTOMapper {
    public static UsuarioDTO mapearUsuario(Usuario usuario){

        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .correo(usuario.getCorreo())
                .password(usuario.getPassword())
                .telefono(usuario.getTelefono())
                .direccion(usuario.getDireccion())
                .fechaCreacion(DateUtils.df.format(usuario.getFechaCreacion()))
                .fechaUltimoIngreso(DateUtils.df.format(usuario.getFechaUltimoIngreso()))
                .rol(usuario.getRol())
                .build();
    }

}
