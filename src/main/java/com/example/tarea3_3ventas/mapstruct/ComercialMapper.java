package com.example.tarea3_3ventas.mapstruct;

import com.example.tarea3_3ventas.domain.Comercial;
import com.example.tarea3_3ventas.dto.ComercialDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComercialMapper {

    //    @Mapping(target = "media", source = "media")
//    @Mapping(target = "total", source = "total")
    public ComercialDTO comercialAComercialDTO(Comercial comercial, Double total,Double media, Double maximoPedido, Double minimoPedido);


    public Comercial comercialDTOAConercial(ComercialDTO comercialDTO);

}
