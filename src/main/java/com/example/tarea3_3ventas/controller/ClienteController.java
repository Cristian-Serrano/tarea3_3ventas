package com.example.tarea3_3ventas.controller;

import com.example.tarea3_3ventas.domain.Cliente;
import com.example.tarea3_3ventas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping({"/clientes", "/clients"})
    public String listar(Model model){

        List<Cliente> listAllCli =  clienteService.listAll();
        model.addAttribute("listaClientes", listAllCli);

        return "clientes";
    }
}
