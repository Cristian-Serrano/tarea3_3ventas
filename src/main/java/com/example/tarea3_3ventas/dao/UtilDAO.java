package com.example.tarea3_3ventas.dao;

import com.example.tarea3_3ventas.domain.Cliente;
import com.example.tarea3_3ventas.domain.Comercial;
import com.example.tarea3_3ventas.domain.Pedido;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilDAO {
    public static Pedido newPedido(ResultSet rs) throws SQLException {
        return new Pedido(rs.getInt("id"),
                rs.getDouble("total"),
                rs.getDate("fecha"),
                new Cliente(rs.getInt("C.id"),
                        rs.getString("C.nombre"),
                        rs.getString("C.apellido1"),
                        rs.getString("C.apellido2"),
                        rs.getString("C.ciudad"),
                        rs.getInt("C.categoria")
                ),
                new Comercial(rs.getInt("CO.id"),
                        rs.getString("CO.nombre"),
                        rs.getString("CO.apellido1"),
                        rs.getString("CO.apellido2"),
                        rs.getBigDecimal("CO.comision")
                )
        );
    }

    public static Cliente newCliente(ResultSet rs) throws SQLException{
            return new Cliente(rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido1"),
                    rs.getString("apellido2"),
                    rs.getString("ciudad"),
                    rs.getInt("categoria")
            );
    }
}
