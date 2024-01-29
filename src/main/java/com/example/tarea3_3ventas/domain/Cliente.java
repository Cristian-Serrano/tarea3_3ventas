package com.example.tarea3_3ventas.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Min(value=1,message = "#{msg.valid.min}")
    private int id;

    @NotBlank(message = "#{msg.valid.notBlank}")
    @Size(max=30, message = "#{msg.valid.size.nombre}")
    private String nombre;

    @NotBlank(message = "#{msg.valid.notBlank}")
    @Size(max=30, message = "#{msg.valid.size.apellido1}")
    private String apellido1;
    private String apellido2;

    @NotBlank(message = "#{msg.valid.notBlank}")
    @Size(max=50, message = "#{msg.valid.size.ciudad}")
    private String ciudad;

    @Min(value=100,message = "#{msg.valid.min}")
    @Max(value=900,message = "#{msg.valid.min}")
    @Pattern(regexp = "\\d00", message = "#{msg.valid.categoria}")
    private int categoria;
}
