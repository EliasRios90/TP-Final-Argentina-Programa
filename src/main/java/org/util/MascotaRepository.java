package org.util;

import org.entidades.Mascota;
import org.persistencia.MascotaJpaController;
import org.persistencia.excepciones.NonexistentEntityException;

import java.util.List;
import java.util.stream.Collectors;

public class MascotaRepository implements MascotaDAO {
    private MascotaJpaController mascotaJpaController;
    public MascotaRepository(){this.mascotaJpaController = new MascotaJpaController();}
    @Override
    public void guardar(Mascota mascota) {
        this.mascotaJpaController.create(mascota);
    }

    @Override
    public void actualizar(Mascota mascota) {
        try {
            this.mascotaJpaController.edit(mascota);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mascota obtenerPorID(Integer id) {
        return this.mascotaJpaController.findMascota(id);
    }

    @Override
    public void eliminar(Integer id) {
        try {
            this.mascotaJpaController.destroy(id);
        } catch (NonexistentEntityException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Mascota> obtenerTodos() {
        return this.mascotaJpaController.findMascotaEntities();
    }

    @Override
    public List<Mascota> obtenerTiposMascotas(String tipoMascota){
        List<Mascota> listaMascotas = this.mascotaJpaController.findMascotaEntities();
        return listaMascotas.stream()
                .filter(mascota -> mascota.obtenerTipoMascota(mascota).equals(tipoMascota))
                .collect(Collectors.toList());
    }

    @Override
    public Integer cantidadMascotas(){
        return this.mascotaJpaController.getMascotaCount();
    }
}
