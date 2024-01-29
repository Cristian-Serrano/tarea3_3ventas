package com.example.tarea3_3ventas.mapstruct;

import com.example.tarea3_3ventas.domain.Cliente;
import com.example.tarea3_3ventas.domain.Comercial;
import com.example.tarea3_3ventas.dto.ClienteDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    public ClienteDTO clienteToClienteDTO(Cliente cliente, List<Comercial> listCom, int pedidosTrimestre, int pedidosSemestre,int pedidosAnio, int pedidosLustro);
    public Cliente clienteDTOToCliente(ClienteDTO clienteDTO);
}
