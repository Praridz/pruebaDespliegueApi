package com.pedrorios.controllers;

import com.pedrorios.dtos.AgendaDTO;
import com.pedrorios.models.responses.ResponseMessageCodeObject;
import com.pedrorios.services.AgendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/agendas")
@CrossOrigin(value = "*")
public class AgendaController {
    private final AgendaService agendaService;

    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AgendaDTO>> obtenerCitas(){
        return new ResponseEntity<>(agendaService.obtenerCitas(), HttpStatus.OK);
    }

    @GetMapping(value="/pendientes",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessageCodeObject> obtenerTodasCitasPendientes(){
        ResponseMessageCodeObject respuesta = agendaService.obtenerTodasCitasPendientes();
        return new ResponseEntity<>(respuesta, HttpStatus.resolve(respuesta.getCode()));
    }


    @GetMapping(value = "/pendientes/medico/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AgendaDTO>> obtenerCitasPendientesMedico(@PathVariable int id){
        return new ResponseEntity<>(agendaService.obtenerCitasPendientesMedico(id), HttpStatus.OK);
    }

    @GetMapping(value = "/pendientes/paciente/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessageCodeObject> obtenerCitasPendientesPaciente(@PathVariable int id){
        ResponseMessageCodeObject respuesta = agendaService.obtenerCitasPendientesPaciente(id);
        return new ResponseEntity<>(respuesta, HttpStatus.resolve(respuesta.getCode()));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessageCodeObject> crearCita(@Valid @RequestBody AgendaDTO agendaDTO) {
        ResponseMessageCodeObject respuesta = agendaService.crearCita(agendaDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.resolve(respuesta.getCode()));
    }

    @PutMapping(value = "/cancelar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessageCodeObject> cancelarCita(@PathVariable String id) {
        ResponseMessageCodeObject respuesta = agendaService.cancelarCita(id);
        return new ResponseEntity<>(respuesta, HttpStatus.resolve(respuesta.getCode()));
    }

    @PutMapping(value = "/editar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessageCodeObject> editar(@Valid @RequestBody AgendaDTO agendaDTO, @PathVariable String id) {
        ResponseMessageCodeObject respuesta = agendaService.editarCita(agendaDTO,id);
        return new ResponseEntity<>(respuesta, HttpStatus.resolve(respuesta.getCode()));
    }

}
