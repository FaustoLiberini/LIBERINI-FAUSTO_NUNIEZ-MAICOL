package com.backend.parcial.service;

import com.backend.parcial.dao.IDao;
import com.backend.parcial.dao.imple.OdontologoDaoH2;
import com.backend.parcial.entity.Odontologo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OdontologoServiceTest{
    private static IDao<Odontologo> odontologoIDao = new OdontologoDaoH2();


    @Test
    public void traerTodosLosOdontologosH2Test() {
        List<Odontologo> odontologos = odontologoIDao.buscarTodos();
        assertEquals(2, odontologos.size());

    }
}