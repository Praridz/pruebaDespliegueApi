package com.pedrorios.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgendaDTO {
    private Integer id;

    @NotNull(message = "la fecha de la cita no puede ser nula")
    @NotEmpty(message = "la fecha de la cita no puede ser vacía")
    @Pattern(regexp="^\\d{4}[\\-]?((((0[13578])|(1[02]))[\\-]?(([0-2][0-9])|(3[01])))|(((0[469])|(11))[\\-]?(([0-2][0-9])|(30)))|(02[\\-]?[0-2][0-9]))$", message = "La fecha  no cumple con el formato aaaa-mm-dd")
    private String fecha;

    private int hora_inicio;

    private int hora_fin;

    private int elMedico;

    private int elPaciente;

    private String estado;
}
