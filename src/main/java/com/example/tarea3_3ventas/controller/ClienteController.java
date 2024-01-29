package com.example.tarea3_3ventas.controller;

import com.example.tarea3_3ventas.domain.Cliente;
import com.example.tarea3_3ventas.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

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

    @GetMapping({"clientes/crear","clients/create"})
    public String crear(Model model){

        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);

        return "crearCliente";
    }

    @PostMapping({"clientes/crear","clients/create"})
    public RedirectView submitCrear(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult bindingResult){

        clienteService.create(cliente);

        return new RedirectView("/clientes");
    }

    @GetMapping({"/clientes/{id}","/clients/{id}"})
    public String detalle(Model model, @PathVariable Integer id ){
        model.addAttribute("clienteDTO", clienteService.clienteDTO(id));

        return "detalleCliente";
    }

    @GetMapping({"clientes/editar/{id}","clients/update/{id}"})
    public String editar(Model model, @PathVariable Integer id ){
        model.addAttribute("cliente", clienteService.one(id));

        return "editarCliente";
    }

    @PostMapping({"/clientes/editar/{id}","/clients/update/{id}"})
    public RedirectView submitEditar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult bindingResult) {

        clienteService.replace(cliente);

        return new RedirectView("/clientes");
    }

    @PostMapping({"/clientes/borrar/{id}","/clients/delete/{id}"})
    public RedirectView borrar(@PathVariable Integer id) {

        clienteService.delete(id);

        return new RedirectView("/clientes");
    }
}
