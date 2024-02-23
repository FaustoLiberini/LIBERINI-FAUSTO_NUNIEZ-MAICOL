package com.backend.parcial.service;
import com.backend.parcial.dao.IDao;
import com.backend.parcial.entity.Odontologo;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> OdontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        OdontologoIDao = odontologoIDao;
    }
    public Odontologo RegistrarOdontologo (Odontologo odontologo){

        return OdontologoIDao.registrar(odontologo);
    }
    public List<Odontologo> buscarTodos(){
        return OdontologoIDao.buscarTodos();
    }
}
