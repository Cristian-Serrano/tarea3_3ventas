package com.example.tarea3_3ventas.service;

import com.example.tarea3_3ventas.dao.PedidoDAO;
import com.example.tarea3_3ventas.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoDAO pedidoDAO;

    public List<Pedido> listAll() {

        return pedidoDAO.getAll();

    }

    public Pedido one(Integer id) {
        Optional<Pedido> optPed = pedidoDAO.find(id);
        if (optPed.isPresent())
            return optPed.get();
        else
            return null;
    }

    public void create(Pedido pedido) {

        pedidoDAO.create(pedido);

    }

    public void replace(Pedido pedido) {

        pedidoDAO.update(pedido);

    }

    public void delete(int id) {

        pedidoDAO.delete(id);

    }
}
