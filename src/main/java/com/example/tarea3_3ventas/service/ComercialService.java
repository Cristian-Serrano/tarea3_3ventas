package com.example.tarea3_3ventas.service;

import com.example.tarea3_3ventas.dao.ComercialDAO;
import com.example.tarea3_3ventas.domain.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComercialService {

    @Autowired
    private ComercialDAO comercialDAO;

    public List<Comercial> listAll() {

        return comercialDAO.getAll();

    }

    public Comercial one(Integer id) {
        Optional<Comercial> optCli = comercialDAO.find(id);
        if (optCli.isPresent())
            return optCli.get();
        else
            return null;
    }

    public void create(Comercial cliente) {

        comercialDAO.create(cliente);

    }

    public void replace(Comercial cliente) {

        comercialDAO.update(cliente);

    }

    public void delete(int id) {

        comercialDAO.delete(id);

    }
}
