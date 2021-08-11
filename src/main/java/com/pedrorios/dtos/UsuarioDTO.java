package com.pedrorios.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Integer id;

    private String nombre;

    private String apellido;

    private String correo;

    private String password;

    private String telefono;

    private String direccion;

    private String fechaCreacion;

    private String fechaUltimoIngreso;

    private String rol;
}
