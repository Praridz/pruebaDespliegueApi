package com.pedrorios.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicoDTO {

    private Integer id;

    private String nombre;

    private String apellido;

    private String estado;

    private int cedula;
}
