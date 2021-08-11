package com.pedrorios.repositories;

import com.pedrorios.models.entities.tables.Medico;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MedicoRepository extends CrudRepository<Medico,Integer> {
    @Override
    List<Medico> findAll();

    List<Medico> findAllByEstado(String estado);
}
