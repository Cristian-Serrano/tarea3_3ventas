package com.example.tarea3_3ventas.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comercial {
    @Min(value=1,message = "#{msg.valid.min}")
    private int id;

    @NotBlank(message = "#{msg.valid.notBlank}")
    @Size(max=30, message = "#{msg.valid.size.nombre}")
    private String nombre;

    @NotBlank(message = "#{msg.valid.notBlank}")
    @Size(max=30, message = "#{msg.valid.size.apellido1}")
    private String apellido1;
    private String apellido2;

    @DecimalMin(value="0.276", inclusive=true, message = "#{msg.valid.min}")
    @DecimalMax(value="0.946", inclusive=true, message = "#{msg.valid.max}")
    private BigDecimal comision;
}
