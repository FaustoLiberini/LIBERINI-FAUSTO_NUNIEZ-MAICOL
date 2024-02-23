package com.backend.parcial.dao.imple;

import com.backend.parcial.dao.IDao;
import com.backend.parcial.dbconnection.H2Connection;
import com.backend.parcial.entity.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {
    private final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = null;
        Odontologo odontologoRegistrado = null;

        try {

            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);


            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO odontologos (nombre, apellido, matricula) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setInt(3, odontologo.getMatricula());
            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            odontologoRegistrado = new Odontologo(odontologo.getNombre(), odontologo.getApellido(), odontologo.getMatricula());

            while (rs.next()) {
                odontologoRegistrado.setId(rs.getInt("id"));
            }

            connection.commit();
            LOGGER.info("Se reguistro un Odontologo: " + odontologoRegistrado);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("Tuvimos un problema");
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try {
            connection = H2Connection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM odontologos");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Odontologo odontologo = crearObjetoOdontologo(rs);
                odontologos.add(odontologo);
            }
            LOGGER.info("Listado de todos los odontologos: " + odontologos);

        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("Ha ocurrido un error al intentar cerrar la base de datos. " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la base de datos. " + ex.getMessage());
                ex.printStackTrace();
            }

        }
        return odontologos;
    }

    private Odontologo crearObjetoOdontologo(ResultSet rs) throws SQLException {
        return new Odontologo(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getInt("matricula"));
    }

}

