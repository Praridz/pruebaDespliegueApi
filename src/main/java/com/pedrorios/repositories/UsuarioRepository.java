package com.pedrorios.repositories;

import com.pedrorios.models.entities.tables.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Integer> {

    @Override
    List<Usuario> findAll();

    Usuario findByCorreoAndPassword(String correo, String password);

    List<Usuario> findAllById(int id);
}
