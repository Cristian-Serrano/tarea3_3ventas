package com.example.tarea3_3ventas.dao;

import com.example.tarea3_3ventas.domain.Comercial;
import com.example.tarea3_3ventas.domain.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoDAO {
    public void create(Pedido pedido);

    public List<Pedido> getAll();

    public List<Pedido> getPedByIdCommercial(int comercialId);
    public List<Pedido> getPedByIdCliente(int clienteId);
    public Optional<Pedido> find(int id);

    public void update(Pedido pedido);

    public void delete(long id);


}