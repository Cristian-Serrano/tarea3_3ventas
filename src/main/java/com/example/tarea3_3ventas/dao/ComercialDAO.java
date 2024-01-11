package com.example.tarea3_3ventas.dao;

import com.example.tarea3_3ventas.domain.Comercial;

import java.util.List;
import java.util.Optional;

public interface ComercialDAO {
    public void create(Comercial comercial);

    public List<Comercial> getAll();
    public Optional<Comercial> find(int id);

    public void update(Comercial comercial);

    public void delete(long id);
}
