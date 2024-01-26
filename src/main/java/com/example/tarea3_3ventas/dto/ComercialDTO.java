package com.example.tarea3_3ventas.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComercialDTO {
    @Min(value=1,message = "#{msg.valid.min}")
    private int id;

    @NotBlank
    private String nombre;
    private String apellido1;
    private String apellido2;
    private float comision;

    private Double total;
    private Double media;
    private Double maximoPedido;
    private Double minimoPedido;


}
