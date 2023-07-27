/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.sinc0138.bouncer.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;

/**
 *
 * @author Donald
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    /**
     * Abstract method creates a new bouncer
     * @param entity body of post
     * @return created or bad request based on body
     */
    public Response create(T entity) {
        try {
            getEntityManager().persist(entity);
            return Response.status(Response.Status.CREATED).entity(entity).build(); 
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Cannot Create Entity").build();
        }
    }

    /**
     * abstract method that returns response of an edited bouncer
     * @param entity
     * @return 
     */
    public Response edit(T entity) {
        getEntityManager().merge(entity);
        return Response.status(Response.Status.CREATED).entity(entity).build();
        
    }

    /**
     * find abstract method, finds a specific ID in our database.
     * @param id
     * @return found or not found
     */
    public Response find(Object id) {
        T entity = getEntityManager().find(entityClass, id);
        return (entity == null)
                ? Response.status(Response.Status.NOT_FOUND).entity("Bouncer with ID: " + id + " Not Found").build()
                : Response.status(Response.Status.OK).entity(entity).build();
    }

    /**
     * Returns Response with all bouncers in the database
     * @return response of bouncers
     */
    public Response findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        List<T> list = getEntityManager().createQuery(cq).getResultList();
        return Response.status(Response.Status.OK).entity(list).build();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
