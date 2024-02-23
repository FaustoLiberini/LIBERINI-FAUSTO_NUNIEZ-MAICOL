package com.backend.parcial.dao.imple;

import com.backend.parcial.dbconnection.H2Connection;
import com.backend.parcial.dao.IDao;
import com.backend.parcial.entity.Odontologo;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import org.apache.log4j.Logger;

public class OdontologoDaoH2 implements IDao<Odontologo> {
        private final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);
         @Override
         public Odontologo registrar(Odontologo odontologo) {
            Connection connection = null;
            Odontologo Odontologo = null;
           try  {
                String insertar = "INSERT INTO odontologos (nombre, apellido, matricula) VALUES (?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertar, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, odontologo.getNombre());
               preparedStatement.setString(2, odontologo.getApellido());
                 preparedStatement.setInt(3, odontologo.getMatricula());
               preparedStatement.execute();
               ResultSet rs = preparedStatement.getGeneratedKeys();
               if (rs.next()) {
                     odontologo.setId(rs.getInt(1));
               }
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
             List<Odontologo> odontologos = new ArrayList<>();
                 Connection connection = null;
             try  {
                 String query = "SELECT * FROM odontologos";
                 PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                     odontologos.add(new Odontologo(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("matricula"), rs.getInt("id")));
                }
            } catch (SQLException e) {
                 LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + e.getMessage());
                 e.printStackTrace();
             }
             return odontologos;
        }

    }

