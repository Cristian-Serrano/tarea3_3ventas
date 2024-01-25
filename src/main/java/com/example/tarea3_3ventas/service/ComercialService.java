package com.example.tarea3_3ventas.service;

import com.example.tarea3_3ventas.dao.ComercialDAO;
import com.example.tarea3_3ventas.dao.PedidoDAO;
import com.example.tarea3_3ventas.domain.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComercialService {

    @Autowired
    private ComercialDAO comercialDAO;
    @Autowired
    private PedidoDAO pedidoDAO;

    public List<Comercial> listAll() {

        return comercialDAO.getAll();

    }

    public List<com.example.tarea3_3ventas.domain.Pedido> listPed(int comercialId) {

        return pedidoDAO.getPedByIdCommercial(comercialId);

    }

    public Comercial one(Integer id) {
        Optional<Comercial> optCo = comercialDAO.find(id);
        if (optCo.isPresent())
            return optCo.get();
        else
            return null;
    }

    public void create(Comercial comercial) {

        comercialDAO.create(comercial);

    }

    public void replace(Comercial comercial) {

        comercialDAO.update(comercial);

    }

    public void delete(int id) {

        comercialDAO.delete(id);

    }

    public Double total(Comercial comercial){
        Optional<Double> maximoOpt = listPed(comercial.getId()).stream()
                .map(p -> p.getTotal())
                .reduce(Double::max);

        return maximoOpt.get();
    }

    public Double media(Comercial comercial){

    }
}
