package com.pedrorios.services;

import com.pedrorios.constants.EstadosConstants;
import com.pedrorios.dtos.MedicoDTO;
import com.pedrorios.mappers.MedicoDTOMapper;
import com.pedrorios.models.entities.tables.Medico;
import com.pedrorios.models.responses.ResponseMessageCodeObject;
import com.pedrorios.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MedicoService {
    private static Logger logger = LoggerFactory.getLogger(MedicoService.class);

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public List<MedicoDTO> obtenerMedicos(){
        logger.info("/obtenerMedicos is called");
        return medicoRepository.findAll().stream()
                .map(MedicoDTOMapper::mapearMedico)
                .collect(Collectors.toList());
    }

    public ResponseMessageCodeObject obtenerMedicosActivos(){
        logger.info("/obtenerMedicosActivos is called");
        Integer responseCode = 500;
        String message = "Correcto";
        Object object = null;
        try{
            List<MedicoDTO> lista = medicoRepository.findAllByEstado(EstadosConstants.ACTIVO).stream()
                    .map(MedicoDTOMapper::mapearMedico)
                    .collect(Collectors.toList());
            object = lista;
            responseCode = 200;
            logger.info("-Los medicos activos fueron obtenidos correctamente");

        }catch (Exception e){
            log.error("No se pudo devolver la lista de medicos activos");
            object = "Falló";
            message = "No se pudo devolver la lista de medicos activos";
        }
        return ResponseMessageCodeObject.builder()
                .code(responseCode)
                .message(message)
                .object(object)
                .build();
    }

    public ResponseMessageCodeObject crearMedico(MedicoDTO medicoDTO) {
        logger.info("/crearMedico is called with the body {} ", medicoDTO);
        Integer responseCode = 500;
        String message = "Se creo correctamente al medico";
        Object object = null;
        try {
            medicoRepository.save(Medico.builder()
                    .nombre(medicoDTO.getNombre())
                    .apellido(medicoDTO.getApellido())
                    .estado(EstadosConstants.ACTIVO)
                    .cedula(medicoDTO.getCedula())
                    .build());
            object = medicoDTO;
            responseCode = 201;
            logger.info("-El medico fue creado exitosamente");

        } catch (Exception e) {
            log.error("No se pudo crear el medico");
            object = "Falló";
            message = "No se pudo crear el medico";
        }
        return ResponseMessageCodeObject.builder()
                .code(responseCode)
                .message(message)
                .object(object)
                .build();
    }

    public ResponseMessageCodeObject actualizarMedico(String id) {
        logger.info("/actualizarMedico is called with the path {}", id);
        Integer responseCode = 500;
        String message = "Se desactivo correctamente al medico";
        Object object = null;
        try {
            Optional<Medico> medico = medicoRepository.findById(Integer.parseInt(id));
            medico.get().setEstado(EstadosConstants.INACTIVO);
            medicoRepository.save(medico.get());
            object = medico;
            responseCode = 200;
            logger.info("-El medico fue desactivado exitosamente");

        } catch (Exception e) {
            log.error("No se pudo eliminar al medico con id: " + id);
            object = "Falló";
            message = "No se pudo eliminar al medico con id: " + id;
        }
        return ResponseMessageCodeObject.builder()
                .code(responseCode)
                .message(message)
                .object(object)
                .build();

    }
}
