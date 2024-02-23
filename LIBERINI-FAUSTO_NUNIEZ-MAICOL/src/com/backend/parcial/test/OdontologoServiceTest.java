package com.backend.parcial.test;

import com.backend.parcial.dao.IDao;
import com.backend.parcial.dao.imple.OdontologoDaoH2;
import com.backend.parcial.dao.imple.OdontologoDaoMemoria;
import com.backend.parcial.entity.Odontologo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OdontologoServiceTest {
    private static IDao<Odontologo> odontologoIDao = new OdontologoDaoH2();


    @Test
    public void traerTodosLosOdontologosH2Test() {
        List<Odontologo> odontologos = odontologoIDao.buscarTodos();
        assertEquals(2, odontologos.size());

    }

    ;

    @Test
    public void traerTodosLosOdontologosEnMemoriaTest() {

        List<Odontologo> odontologos = new ArrayList<>();
        odontologos.add(new Odontologo(1, "Renata", "Perez", 1323));
        odontologos.add(new Odontologo(2, "Maria", "Sanchez", 753));

        OdontologoDaoMemoria daoMemoria = new OdontologoDaoMemoria(odontologos);

        List<Odontologo> todosLosOdontologos = daoMemoria.buscarTodos();

        assertEquals(odontologos, todosLosOdontologos);


    }
}