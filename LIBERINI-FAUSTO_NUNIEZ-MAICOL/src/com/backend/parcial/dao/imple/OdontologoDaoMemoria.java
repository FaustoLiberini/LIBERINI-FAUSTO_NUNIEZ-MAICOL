package com.backend.parcial.dao.imple;

import com.backend.parcial.dao.IDao;
import com.backend.parcial.entity.Odontologo;
import org.apache.log4j.Logger;

import java.util.List;

public class OdontologoDaoMemoria implements IDao<Odontologo> {

    private final Logger LOGGER = Logger.getLogger(OdontologoDaoMemoria.class);
    private List<Odontologo> odontologoRepository;

    public OdontologoDaoMemoria(List<Odontologo> odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        int id = odontologoRepository.size() + 1;
        Odontologo odontologoGuardado = new Odontologo(id, odontologo.getNombre(), odontologo.getApellido(), odontologo.getMatricula());
        odontologoRepository.add(odontologoGuardado);
        LOGGER.info("Odontologo guardado en memoria " + odontologoGuardado);
        return odontologoGuardado;
    }


    @Override
    public List<Odontologo> buscarTodos() {
        LOGGER.info("Buscando todos los odontologos en memoria");
        for (Odontologo odontologo : odontologoRepository) {
            LOGGER.info(odontologo);
        }
        return odontologoRepository;

    }
}
