package com.pedrorios.controllers;

import com.pedrorios.dtos.LoginDTO;
import com.pedrorios.dtos.UsuarioDTO;
import com.pedrorios.models.responses.ResponseMessageCodeObject;
import com.pedrorios.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(value = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuarios(){
        return new ResponseEntity<>(usuarioService.obtenerUsuarios(), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> crearPaciente(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        return new ResponseEntity<>(usuarioService.crearPaciente(usuarioDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO, @PathVariable String id) {
        return new ResponseEntity<>(usuarioService.actualizarUsuario(usuarioDTO, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable String id) {
        int response = usuarioService.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.resolve(response));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseMessageCodeObject> loguearse(@Valid @RequestBody LoginDTO loginDTO) {
        ResponseMessageCodeObject respuesta = usuarioService.loguearse(loginDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.resolve(respuesta.getCode()));
    }
}
