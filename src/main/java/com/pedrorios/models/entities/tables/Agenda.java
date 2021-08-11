package com.pedrorios.models.entities.tables;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "agenda")
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "hora_inicio")
    private int hora_inicio;

    @Column(name = "hora_fin")
    private int hora_fin;

    @Column(name = "elmedico")
    private int elMedico;

    @Column(name = "elpaciente")
    private int elPaciente;

    @Column(name = "estado")
    private String estado;
}
