package com.example.tarea3_3ventas.dao;

import com.example.tarea3_3ventas.domain.Comercial;
import com.example.tarea3_3ventas.domain.Comercial;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Slf4j
@Repository
public class ComercialDAOImpl implements ComercialDAO {

    private JdbcTemplate jdbcTemplate;

    public ComercialDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Comercial comercial) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert
                .withTableName("comercial")
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("nombre",comercial.getNombre())
                .addValue("apellido1",comercial.getApellido1())
                .addValue("apellido2",comercial.getApellido2())
                .addValue("comision",comercial.getComision());
        Number number = simpleJdbcInsert.executeAndReturnKey(params);

        comercial.setId(number.intValue());

        log.info("Insertados {} registros.", number.intValue());
    }

    @Override
    public List<Comercial> getAll() {

        List<Comercial> listComerciales = jdbcTemplate.query(
                "SELECT * FROM comercial",
                (rs, rowNum) -> new Comercial(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getFloat("comision")

                )
        );

        log.info("Devueltos {} registros.", listComerciales.size());

        return listComerciales;
    }

    @Override
    public Optional<Comercial> find(int id) {
        Comercial comercial =  jdbcTemplate
                .queryForObject("SELECT * FROM comercial WHERE id = ?"
                        , (rs, rowNum) -> new Comercial(rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("apellido1"),
                                rs.getString("apellido2"),
                                rs.getFloat("comision"))
                        , id
                );

        if (comercial != null) {
            return Optional.of(comercial);}
        else {
            log.info("Comercial no encontrado.");
            return Optional.empty(); }
    }

    @Override
    public void update(Comercial comercial) {
        int rows = jdbcTemplate.update("""
										UPDATE comercial SET 
														nombre = ?, 
														apellido1 = ?, 
														apellido2 = ?,
														comision = ?
												WHERE id = ?
										""", comercial.getNombre()
                , comercial.getApellido1()
                , comercial.getApellido2()
                , comercial.getComision()
                , comercial.getId());

        log.info("Update de Comercial con {} registros actualizados.", rows);

    }

    @Override
    public void delete(long id) {

        int rows = jdbcTemplate.update("DELETE FROM comercial WHERE id = ?", id);

        log.info("Delete de Comercial con {} registros eliminados.", rows);
    }
}
