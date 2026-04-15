package com.Quirilao_Josefa.Evaluacion1.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Problema {

    private static final AtomicInteger contador = new AtomicInteger(1);

    private int id;
    
    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 100, message = "El título debe tener entre 3 y 100 caracteres")
    private String titulo;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 10, max = 500, message = "La descripción debe tener entre 10 y 500 caracteres")
    private String descripcion;

    @NotNull(message = "El estado es obligatorio")
    private EstadoProblema estado;

    @NotNull(message = "La prioridad es obligatoria")
    private Prioridad prioridad;

    @NotBlank(message = "El usuario que reporta es obligatorio")
    private String usuarioReporta;

    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaActualizacion;

    public Problema(String titulo, String descripcion, EstadoProblema estado,
                      Prioridad prioridad, String usuarioReporta) {
        this.id = contador.getAndIncrement();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.prioridad = prioridad;
        this.usuarioReporta = usuarioReporta;
        this.fechaRegistro = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

}
