package com.example.tarea3_3ventas.service;

import com.example.tarea3_3ventas.dao.ClienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteDAO clienteDAO;
}
