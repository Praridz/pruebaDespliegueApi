package com.pedrorios.repositories;

import com.pedrorios.models.entities.tables.Agenda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaRepository extends CrudRepository<Agenda,Integer> {

    @Override
    List<Agenda> findAll();

    Agenda findAllById(int id);

    List<Agenda> findAllByEstado(String estado);

    List<Agenda> findAllByElMedicoAndEstado(int elMedico, String estado);

    List<Agenda> findAllByElPacienteAndEstado(int elPaciente, String estado);
}
