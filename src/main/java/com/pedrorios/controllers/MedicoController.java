package com.pedrorios.controllers;

import com.pedrorios.dtos.MedicoDTO;
import com.pedrorios.dtos.UsuarioDTO;
import com.pedrorios.models.entities.tables.Medico;
import com.pedrorios.models.responses.ResponseMessageCodeObject;
import com.pedrorios.services.MedicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/medicos")
@CrossOrigin(value = "*")
public class MedicoController {
    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MedicoDTO>> obtenerMedicos(){
        return new ResponseEntity<>(medicoService.obtenerMedicos(), HttpStatus.OK);
    }

    @GetMapping(value = "/activos",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessageCodeObject> obtenerMedicosActivos(){
        ResponseMessageCodeObject respuesta = medicoService.obtenerMedicosActivos();
        return new ResponseEntity<>(respuesta, HttpStatus.resolve(respuesta.getCode()));

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessageCodeObject> crearMedico(@Valid @RequestBody MedicoDTO medicoDTO) {
        ResponseMessageCodeObject respuesta = medicoService.crearMedico(medicoDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.resolve(respuesta.getCode()));

    }

    @PutMapping(value = "/eliminar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessageCodeObject> eliminarMedico(@PathVariable String id) {
        ResponseMessageCodeObject respuesta = medicoService.actualizarMedico(id);
        return new ResponseEntity<>(respuesta, HttpStatus.resolve(respuesta.getCode()));

    }
}
