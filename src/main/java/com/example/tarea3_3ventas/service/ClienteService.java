package com.example.tarea3_3ventas.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.tarea3_3ventas.dao.ClienteDAO;
import com.example.tarea3_3ventas.dao.ComercialDAO;
import com.example.tarea3_3ventas.dao.PedidoDAO;
import com.example.tarea3_3ventas.domain.Cliente;
import com.example.tarea3_3ventas.domain.Pedido;
import com.example.tarea3_3ventas.dto.ClienteDTO;
import com.example.tarea3_3ventas.mapstruct.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private ComercialDAO comercialDAO;

    @Autowired
    private ClienteDAO clienteDAO;

    @Autowired
    private PedidoDAO pedidoDAO;

    public List<Cliente> listAll() {

        return clienteDAO.getAll();

    }

    public Cliente one(Integer id) {
        Optional<Cliente> optCli = clienteDAO.find(id);
        if (optCli.isPresent())
            return optCli.get();
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



    public ClienteDTO clienteDTO(int idCliente){

        Date fechaActual = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaActual);
        cal.add(Calendar.MONTH, -3);
        Date haceTresMeses = cal.getTime();
        cal.add(Calendar.MONTH, -3);
        Date haceSeisMeses = cal.getTime();
        cal.add(Calendar.MONTH, -6);
        Date haceUnAnio = cal.getTime();
        cal.add(Calendar.YEAR, -4);
        Date haceUnLustro = cal.getTime();

        List<Pedido> listPed = pedidoDAO.getPedByIdCliente(idCliente);
        int pedidosTrimestre = (int)listPed.stream()
                .map(pedido -> pedido.getFecha())
                .filter((Date fecha) -> fecha.after(haceTresMeses))
                .count();

        int pedidosSemestre = (int)listPed.stream()
                .map(pedido -> pedido.getFecha())
                .filter((Date fecha) -> fecha.after(haceSeisMeses))
                .count();

        int pedidosAnio = (int)listPed.stream()
                .map(pedido -> pedido.getFecha())
                .filter((Date fecha) -> fecha.after(haceUnAnio))
                .count();

        int pedidosLustro = (int)listPed.stream()
                .map(pedido -> pedido.getFecha())
                .filter((Date fecha) -> fecha.after(haceUnLustro))
                .count();

        return clienteMapper.clienteToClienteDTO(
                one(idCliente),
                comercialDAO.getListComercialByIdCliente(idCliente),
                pedidosTrimestre,
                pedidosSemestre,
                pedidosAnio,
                pedidosLustro
        );
    }

}