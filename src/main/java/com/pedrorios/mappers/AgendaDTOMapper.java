package com.pedrorios.mappers;

import com.pedrorios.dtos.AgendaDTO;
import com.pedrorios.models.entities.tables.Agenda;
import com.pedrorios.utils.DateUtils;

public class AgendaDTOMapper {
    public static AgendaDTO mapearAgenda(Agenda agenda){

        return AgendaDTO.builder()
                .id(agenda.getId())
                .fecha(DateUtils.df.format(agenda.getFecha()))
                .hora_inicio(agenda.getHora_inicio())
                .hora_fin(agenda.getHora_fin())
                .elMedico(agenda.getElMedico())
                .elPaciente(agenda.getElPaciente())
                .estado(agenda.getEstado())
                .build();
    }
}
