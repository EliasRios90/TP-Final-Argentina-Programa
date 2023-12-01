package org.util;

import org.entidades.Prestacion;

import java.util.List;

public interface PrestacionDAO {
    void guardar(Prestacion prestacion);
    void actualizar(Prestacion prestacion);
    Prestacion obtenerPorID(Integer id);
    void eliminar(Integer id);
    List<Prestacion> obtenerTodos();
}
