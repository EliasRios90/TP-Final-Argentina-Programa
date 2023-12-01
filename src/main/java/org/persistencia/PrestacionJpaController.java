package org.persistencia;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.entidades.Prestacion;
import org.persistencia.excepciones.NonexistentEntityException;

import java.io.Serializable;
import java.util.List;

public class PrestacionJpaController implements Serializable {

    public PrestacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public PrestacionJpaController() {
        emf = Persistence.createEntityManagerFactory("org.veterinaria");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Prestacion prestacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            // Si está en estado "detached", se puede utilizar merge para volver a adjuntar la entidad antes de persistirla.
            if (!em.contains(prestacion)) {
                prestacion = em.merge(prestacion);
            }

            em.persist(prestacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Prestacion prestacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            prestacion = em.merge(prestacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = prestacion.getId();
                if (findMascota(id) == null) {
                    throw new NonexistentEntityException("The prestacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prestacion prestacion;
            try {
                prestacion = em.getReference(Prestacion.class, id);
                prestacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prestacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(prestacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Prestacion> findMascotaEntities() {
        return findMascotaEntities(true, -1, -1);
    }

    public List<Prestacion> findMascotaEntities(int maxResults, int firstResult) {
        return findMascotaEntities(false, maxResults, firstResult);
    }

    private List<Prestacion> findMascotaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Prestacion.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Prestacion findMascota(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Prestacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getMascotaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Prestacion> rt = cq.from(Prestacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
