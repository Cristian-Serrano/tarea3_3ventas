package com.example.tarea3_3ventas.service;

import java.util.List;
import java.util.Optional;

import com.example.tarea3_3ventas.dao.ClienteDAO;
import com.example.tarea3_3ventas.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteDAO clienteDAO;

    public List<Cliente> listAll() {

        return clienteDAO.getAll();

    }

    public Cliente one(Integer id) {
        Optional<Cliente> optFab = clienteDAO.find(id);
        if (optFab.isPresent())
            return optFab.get();
        else
            return null;
    }

    public void create(Cliente cliente) {

        clienteDAO.create(cliente);

    }

    public void replace(Cliente cliente) {

        clienteDAO.update(cliente);

    }

    public void delete(int id) {

        clienteDAO.delete(id);

    }

}