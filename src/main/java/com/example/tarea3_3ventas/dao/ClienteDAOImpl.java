package com.example.tarea3_3ventas.dao;

import com.example.tarea3_3ventas.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteDAOImpl implements ClienteDAO{

    private JdbcTemplate jdcbTemplate;

    public ClienteDAOImpl(JdbcTemplate jdcbTemplate) {
        this.jdcbTemplate = jdcbTemplate;
    }

    @Override
    public void create(Cliente cliente) {

    }

    @Override
    public List<Cliente> getAll() {
        return null;
    }

    @Override
    public Optional<Cliente> find(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Cliente cliente) {

    }

    @Override
    public void delete(long id) {

    }
}
