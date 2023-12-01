package org.util;

import org.entidades.Prestacion;
import org.persistencia.PrestacionJpaController;
import org.persistencia.excepciones.NonexistentEntityException;

import java.util.List;

public class PrestacionRepository implements PrestacionDAO {
    private PrestacionJpaController prestacionJpaController;
    public PrestacionRepository(){this.prestacionJpaController = new PrestacionJpaController();}
    @Override
    public void guardar(Prestacion prestacion) {
        this.prestacionJpaController.create(prestacion);
    }

    @Override
    public void actualizar(Prestacion prestacion) {
        try {
            this.prestacionJpaController.edit(prestacion);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Prestacion obtenerPorID(Integer id) {
        return this.prestacionJpaController.findMascota(id);
    }

    @Override
    public void eliminar(Integer id) {
        try {
            this.prestacionJpaController.destroy(id);
        } catch (NonexistentEntityException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Prestacion> obtenerTodos() {
        return this.prestacionJpaController.findMascotaEntities();
    }
}
