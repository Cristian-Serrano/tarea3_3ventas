package com.example.tarea3_3ventas.dao;

import com.example.tarea3_3ventas.domain.Cliente;
import com.example.tarea3_3ventas.domain.Pedido;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Slf4j
@Repository
public class ClienteDAOImpl implements ClienteDAO{

    private JdbcTemplate jdbcTemplate;

    public ClienteDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Cliente cliente) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert
                .withTableName("cliente")
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("nombre",cliente.getNombre())
                .addValue("apellido1",cliente.getApellido1())
                .addValue("apellido2",cliente.getApellido2())
                .addValue("ciudad",cliente.getCiudad())
                .addValue("categoria",cliente.getCategoria());
        Number number = simpleJdbcInsert.executeAndReturnKey(params);

        cliente.setId(number.intValue());

        log.info("Insertados {} registros.", number.intValue());
    }

    @Override
    public List<Cliente> getAll() {

        List<Cliente> listClientes = jdbcTemplate.query(
                "SELECT * FROM cliente",
                (rs, rowNum) -> new Cliente(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getString("ciudad"),
                        rs.getInt("categoria")
                )
        );

        log.info("Devueltos {} registros.", listClientes.size());

        return listClientes;
    }

    public List<Cliente> getCliByIdCommercial(int comercialId){
        List<Cliente> listClientes = jdbcTemplate.query("""
                            SELECT * FROM cliente C 
                                right join pedido P on P.id_cliente = C.id 
                                right join comercial CO on P.id_comercial = CO.id
                                WHERE P.id_comercial = ?""",
                (rs, rowNum) -> UtilDAO.newCliente(rs),
                comercialId
        );

        log.info("Devueltos {} registros.", listClientes.size());

        return listClientes;
    }

    @Override
    public Optional<Cliente> find(int id) {
        Cliente cliente =  jdbcTemplate
                .queryForObject("SELECT * FROM cliente WHERE id = ?"
                        , (rs, rowNum) -> new Cliente(rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("apellido1"),
                                rs.getString("apellido2"),
                                rs.getString("ciudad"),
                                rs.getInt("categoria"))
                        , id
                );

        if (cliente != null) {
            return Optional.of(cliente);}
        else {
            log.info("Cliente no encontrado.");
            return Optional.empty(); }
    }

    @Override
    public void update(Cliente cliente) {
        int rows = jdbcTemplate.update("""
										UPDATE cliente SET 
														nombre = ?, 
														apellido1 = ?, 
														apellido2 = ?,
														ciudad = ?,
														categoria = ?  
												WHERE id = ?
										""", cliente.getNombre()
                , cliente.getApellido1()
                , cliente.getApellido2()
                , cliente.getCiudad()
                , cliente.getCategoria()
                , cliente.getId());

        log.info("Update de Cliente con {} registros actualizados.", rows);

    }

    @Override
    public void delete(long id) {

        int rows = jdbcTemplate.update("DELETE FROM cliente WHERE id = ?", id);

        log.info("Delete de Cliente con {} registros eliminados.", rows);
    }
}
