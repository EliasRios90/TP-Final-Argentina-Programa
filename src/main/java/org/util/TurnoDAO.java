package org.util;

import org.entidades.Turno;

import java.util.List;

public interface TurnoDAO {
    void guardar(Turno turno);
    void actualizar(Turno turno);
    Turno obtenerPorID(Integer id);
    void eliminar(Integer id);
    List<Turno> obtenerTodos();
    void asignarTurno(Turno turno);
    List<Turno> obtenerTurnosLibres();
    Integer cantidadTurnos();
}
