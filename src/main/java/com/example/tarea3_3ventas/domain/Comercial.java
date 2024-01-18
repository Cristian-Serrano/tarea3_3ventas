package com.example.tarea3_3ventas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comercial {
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private float comision;
}
