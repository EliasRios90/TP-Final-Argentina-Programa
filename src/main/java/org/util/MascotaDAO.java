package org.util;

import org.entidades.Mascota;

import java.util.List;

public interface MascotaDAO {
    void guardar(Mascota mascota);
    void actualizar(Mascota mascota);
    Mascota obtenerPorID(Integer id);
    void eliminar(Integer id);
    List<Mascota> obtenerTodos();
    List<Mascota> obtenerTiposMascotas(String tipoMascota);
    Integer cantidadMascotas();
}
