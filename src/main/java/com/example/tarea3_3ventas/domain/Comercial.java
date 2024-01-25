package com.example.tarea3_3ventas.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comercial {
    @Min(value=1,message = "#{msg.valid.min}")
    private int id;

    @NotBlank
    private String nombre;
    private String apellido1;
    private String apellido2;
    private float comision;
}
