package com.example.tarea3_3ventas.dao;

import com.example.tarea3_3ventas.domain.Pedido;
import com.example.tarea3_3ventas.domain.Pedido;
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
public class PedidoDAOImpl implements PedidoDAO {

    private JdbcTemplate jdbcTemplate;

    public PedidoDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Pedido pedido) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert
                .withTableName("pedido")
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("total",pedido.getTotal())
                .addValue("fecha",pedido.getFecha())
                .addValue("id_cliente",pedido.getId_cliente())
                .addValue("id_comercial",pedido.getId_comercial());
        Number number = simpleJdbcInsert.executeAndReturnKey(params);

        pedido.setId(number.intValue());

        log.info("Insertados {} registros.", number.intValue());
    }

    @Override
    public List<Pedido> getAll() {

        List<Pedido> listPedidos = jdbcTemplate.query(
                "SELECT * FROM pedido",
                (rs, rowNum) -> new Pedido(rs.getInt("id"),
                        rs.getDouble("total"),
                        rs.getDate("fecha"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_comercial")

                )
        );

        log.info("Devueltos {} registros.", listPedidos.size());

        return listPedidos;
    }

    @Override
    public Optional<Pedido> find(int id) {
        Pedido pedido =  jdbcTemplate
                .queryForObject("SELECT * FROM pedido WHERE id = ?"
                        , (rs, rowNum) -> new Pedido(rs.getInt("id"),
                                rs.getDouble("total"),
                                rs.getDate("fecha"),
                                rs.getInt("id_cliente"),
                                rs.getInt("id_comercial"))
                        , id
                );

        if (pedido != null) {
            return Optional.of(pedido);}
        else {
            log.info("Pedido no encontrado.");
            return Optional.empty(); }
    }

    @Override
    public void update(Pedido pedido) {
        int rows = jdbcTemplate.update("""
										UPDATE pedido SET 
														total = ?, 
														fecha = ?, 
														id_cliente = ?,
														id_comercial = ?
												WHERE id = ?
										""", pedido.getTotal()
                , pedido.getFecha()
                , pedido.getId_cliente()
                , pedido.getId_comercial()
                , pedido.getId());

        log.info("Update de Pedido con {} registros actualizados.", rows);

    }

    @Override
    public void delete(long id) {

        int rows = jdbcTemplate.update("DELETE FROM pedido WHERE id = ?", id);

        log.info("Delete de Pedido con {} registros eliminados.", rows);
    }
}
