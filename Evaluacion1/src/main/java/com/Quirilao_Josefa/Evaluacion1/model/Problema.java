package com.Quirilao_Josefa.Evaluacion1.model;

import java.util.concurrent.atomic.AtomicInteger;

//import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//AllArgsConstructor
public class Problema {

    private static final AtomicInteger contador = new AtomicInteger(1);

    
    private int id;
    private String desc_problema;
    private String estado_incidencia;
    private int nivel_prioridad;
    private String usuario_reporta;
    private String fecha_registro;
}
