package com.example.tarea3_3ventas.dto;

import com.example.tarea3_3ventas.domain.Comercial;
import jakarta.validation.constraints.*;

import java.util.List;

public class ClienteDTO {
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

    private List<Comercial> listCom;
}
