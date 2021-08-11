package com.pedrorios.services;

import com.pedrorios.constants.EstadosConstants;
import com.pedrorios.dtos.AgendaDTO;
import com.pedrorios.mappers.AgendaDTOMapper;
import com.pedrorios.models.entities.tables.Agenda;
import com.pedrorios.models.responses.ResponseMessageCodeObject;
import com.pedrorios.repositories.AgendaRepository;
import com.pedrorios.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AgendaService {
    private static Logger logger = LoggerFactory.getLogger(AgendaService.class);

    private final AgendaRepository agendaRepository;

    public AgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public List<AgendaDTO> obtenerCitas(){
        logger.info("/obtenerCitas is called");
        return agendaRepository.findAll().stream()
                .map(AgendaDTOMapper::mapearAgenda)
                .collect(Collectors.toList());
    }

    public ResponseMessageCodeObject obtenerTodasCitasPendientes(){
        logger.info("/obtenerTodasCitasPendientes is called");
        Integer responseCode = 500;
        String message = "Correcto";
        Object object = null;
        try{
            List<AgendaDTO> lista = agendaRepository.findAllByEstado(EstadosConstants.PENDIENTE).stream()
                    .map(AgendaDTOMapper::mapearAgenda)
                    .collect(Collectors.toList());
            object = lista;
            responseCode = 200;
            logger.info("-Se obtuvo la lista de citas pendientes correctamente");
        } catch (Exception e){
            log.error("No se pudo obtener todas las citas pendientes");
            object = "Falló";
            message = "No se pudo obtener todas las citas pendientes";
        }
        return ResponseMessageCodeObject.builder()
                .code(responseCode)
                .message(message)
                .object(object)
                .build();
    }

    public List<AgendaDTO> obtenerCitasPendientesMedico(int elMedico){
        logger.info("/obtenerCitasPendientesMedico is called with the path {} ",elMedico);

        return agendaRepository.findAllByElMedicoAndEstado(elMedico, EstadosConstants.PENDIENTE).stream()
                .map(AgendaDTOMapper::mapearAgenda)
                .collect(Collectors.toList());
    }

    public ResponseMessageCodeObject obtenerCitasPendientesPaciente(int elPaciente){
        logger.info("/obtenerCitasPendientesPaciente is called with the path {} ",elPaciente);

        Integer responseCode = 500;
        String message = "Correcto";
        Object object = null;
        try{
            List<AgendaDTO> lista =  agendaRepository.findAllByElPacienteAndEstado(elPaciente, EstadosConstants.PENDIENTE).stream()
                    .map(AgendaDTOMapper::mapearAgenda)
                    .collect(Collectors.toList());
            object = lista;
            responseCode = 200;
            logger.info("-Se obtuvo la lista de citas pendientes correctamente");
        } catch (Exception e){
            log.error("No se pudo obtener todas las citas pendientes del paciente con id: "+ elPaciente);
            object = "Falló";
            message = "No se pudo obtener todas las citas pendientes del paciente con id: "+ elPaciente;
        }
        return ResponseMessageCodeObject.builder()
                .code(responseCode)
                .message(message)
                .object(object)
                .build();
    }

    public ResponseMessageCodeObject crearCita(AgendaDTO agendaDTO) {
        logger.info("/crearCita is called with the body {} ",agendaDTO);
        Integer responseCode = 500;
        String message = "Se creó la cita con exito";
        Object object = null;
        try {
            agendaRepository.save(Agenda.builder()
                    .fecha(DateUtils.df.parse(agendaDTO.getFecha()))
                    .hora_inicio(agendaDTO.getHora_inicio())
                    .hora_fin(agendaDTO.getHora_fin())
                    .elMedico(agendaDTO.getElMedico())
                    .elPaciente(agendaDTO.getElPaciente())
                    .estado(EstadosConstants.PENDIENTE)
                    .build());
            responseCode = 201;
            object = agendaDTO;
            logger.info("-La cita se creó correctamente");
        } catch (Exception e) {
            log.error("No se pudo crear la cita");
            object = "Falló";
            message = "No se pudo crear la cita";
        }
        return ResponseMessageCodeObject.builder()
                .code(responseCode)
                .message(message)
                .object(object)
                .build();    }

    public ResponseMessageCodeObject cancelarCita(String id) {
        logger.info("/cancelarCita is called with the path {} ",id);
        Integer responseCode = 500;
        String message = "Se cancelo la cita con exito";
        Object object = null;

        try {
            Agenda agenda = agendaRepository.findAllById(Integer.parseInt(id));
            agenda.setEstado(EstadosConstants.CANCELADA);
            agendaRepository.save(agenda);
            responseCode = 200;
            object = agenda;
            logger.info("-Se cancelo la cita correctamente");
        } catch (Exception e) {
            log.error("No se pudo cancelar la cita con id: " + id);
            object = "Falló";
            message = "No se pudo cancelar la cita con id: " + id;
        }

        return ResponseMessageCodeObject.builder()
                .code(responseCode)
                .message(message)
                .object(object)
                .build();
    }

    public ResponseMessageCodeObject editarCita(AgendaDTO agendaDTO,String id) {
        logger.info("/editarCita is called with the path "+id+" and body {} ",agendaDTO);
        Integer responseCode = 500;
        String message = "Se editó correctamente la cita";
        Object object = null;
        try {
            Agenda agenda = agendaRepository.save(Agenda.builder()
                    .id(Integer.parseInt(id))
                    .fecha(DateUtils.df.parse(agendaDTO.getFecha()))
                    .hora_inicio(agendaDTO.getHora_inicio())
                    .hora_fin(agendaDTO.getHora_fin())
                    .elMedico(agendaDTO.getElMedico())
                    .elPaciente(agendaDTO.getElPaciente())
                    .estado(agendaDTO.getEstado())
                    .build());
            object = agenda;
            responseCode = 200;
            logger.info("-Se editó la cita correctamente");
        } catch (Exception e) {
            log.error("No se pudo editar la cita con id: " + id);
            object = "Falló";
            message = "No se pudo editar la cita con id: " + id;
        }
        return ResponseMessageCodeObject.builder()
                .code(responseCode)
                .message(message)
                .object(object)
                .build();
    }
}
