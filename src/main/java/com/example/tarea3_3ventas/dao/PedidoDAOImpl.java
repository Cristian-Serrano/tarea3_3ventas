package com.example.tarea3_3ventas.dao;

import com.example.tarea3_3ventas.domain.Comercial;
import com.example.tarea3_3ventas.domain.Pedido;
import com.example.tarea3_3ventas.domain.Pedido;
import jdk.jshell.execution.Util;
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
                .addValue("id_cliente",pedido.getCliente().getId())
                .addValue("id_comercial",pedido.getComercial().getId());
        Number number = simpleJdbcInsert.executeAndReturnKey(params);

        pedido.setId(number.intValue());

        log.info("Insertados {} registros.", number.intValue());
    }

    @Override
    public List<Pedido> getAll() {

        List<Pedido> listPedidos = jdbcTemplate.query("""
                            SELECT * FROM pedido p 
                                left join cliente C on p.id_clietne = C.id 
                                left join comercial CO on p.id_comercial = CO.id""",
                            (rs, rowNum) -> UtilDAO.newPedido(rs)
        );

        log.info("Devueltos {} registros.", listPedidos.size());

        return listPedidos;
    }

    public List<Pedido> getPedByIdCommercial(int comercialId){
        List<Pedido> listPedidos = jdbcTemplate.query("""
                            SELECT * FROM pedido p 
                                left join cliente C on p.id_cliente = C.id 
                                left join comercial CO on p.id_comercial = CO.id
                                WHERE p.id_comercial = ?""",
                            (rs, rowNum) -> UtilDAO.newPedido(rs),
                            comercialId
        );

        log.info("Devueltos {} registros.", listPedidos.size());

        return listPedidos;
    }

    public List<Pedido> getPedByIdCliente(int clienteId){
        List<Pedido> listPedidos = jdbcTemplate.query("""
                            SELECT * FROM pedido p 
                                left join cliente C on p.id_cliente = C.id 
                                left join comercial CO on p.id_comercial = CO.id
                                WHERE p.id_cliente = ?""",
                (rs, rowNum) -> UtilDAO.newPedido(rs),
                clienteId
        );

        log.info("Devueltos {} registros.", listPedidos.size());

        return listPedidos;
    }

    @Override
    public Optional<Pedido> find(int id) {
        Pedido pedido =  jdbcTemplate
                .queryForObject("""
                            SELECT * FROM pedido p 
                                left join cliente C on p.id_clietne = C.id 
                                left join comercial CO on p.id_comercial = CO.id
                                WHERE p.id = ?""",
                            (rs, rowNum) -> UtilDAO.newPedido(rs),
                            id
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
                , pedido.getCliente().getId()
                , pedido.getComercial().getId()
                , pedido.getId());

        log.info("Update de Pedido con {} registros actualizados.", rows);

    }

    @Override
    public void delete(long id) {

        int rows = jdbcTemplate.update("DELETE FROM pedido WHERE id = ?", id);

        log.info("Delete de Pedido con {} registros eliminados.", rows);
    }
}
