package com.example.tarea3_3ventas.controller;

import com.example.tarea3_3ventas.domain.Cliente;
import com.example.tarea3_3ventas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping({"/clientes/{id}","/clients/{id}"})
    public String detalle(Model model, @PathVariable Integer id ){
        model.addAttribute("cliente", clienteService.one(id));

        return "detalleCliente";
    }

    @GetMapping({"clientes/cambiar/{id}","clients/update/{id}"})
    public String cambiar(Model model, @PathVariable Integer id ){
        model.addAttribute("cliente", clienteService.one(id));

        return "editarCliente";
    }
}
