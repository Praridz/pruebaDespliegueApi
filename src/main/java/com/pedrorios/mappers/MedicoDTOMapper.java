package com.pedrorios.mappers;

import com.pedrorios.dtos.MedicoDTO;
import com.pedrorios.models.entities.tables.Medico;

public class MedicoDTOMapper {
    public static MedicoDTO mapearMedico(Medico medico){
        return MedicoDTO.builder()
                .id(medico.getId())
                .nombre(medico.getNombre())
                .apellido(medico.getApellido())
                .estado(medico.getEstado())
                .cedula(medico.getCedula())
                .build();
    }
}
