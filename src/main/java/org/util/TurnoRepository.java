package org.util;

import org.entidades.Turno;
import org.persistencia.TurnoJpaController;
import org.persistencia.excepciones.NonexistentEntityException;

import java.util.ArrayList;
import java.util.List;

public class TurnoRepository implements TurnoDAO{
    private TurnoJpaController turnoJpaController;
    public TurnoRepository(){this.turnoJpaController = new TurnoJpaController();}
    @Override
    public void guardar(Turno turno) {
        this.turnoJpaController.create(turno);
    }

    @Override
    public void actualizar(Turno turno) {
        try {
            this.turnoJpaController.edit(turno);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Turno obtenerPorID(Integer id) {
        return this.turnoJpaController.findTurno(id);
    }

    @Override
    public void eliminar(Integer id) {
        try {
            this.turnoJpaController.destroy(id);
        } catch (NonexistentEntityException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Turno> obtenerTodos() {
        return this.turnoJpaController.findTurnoEntities();
    }

    @Override
    public void asignarTurno(Turno turno){
        turno.setEstadoTurno("asignado");
        try {
            this.turnoJpaController.edit(turno);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Turno> obtenerTurnosLibres(){
        List<Turno> turnosLibres = new ArrayList<>();
        List<Turno> turnos = obtenerTodos();
        if(turnos.size() > 0){
            for(Turno turno : turnos){
                if(turno.getEstadoTurno().equals("libre"))
                    turnosLibres.add(turno);
            }
            return turnosLibres;
        }else return null;
    }

    @Override
    public Integer cantidadTurnos(){
        return this.turnoJpaController.getTurnoCount();
    }
}
