/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.sinc0138.bouncer.service;

import cst8218.sinc0138.bouncer.entity.Appuser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Response;

/**
 *
 * @author D
 */
@Stateless
@DeclareRoles("{APIGroup,Admin}")
@Path("appuser")
public class AppuserFacadeREST extends AbstractFacade<Appuser> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public AppuserFacadeREST() {
        super(Appuser.class);
    }

    /**
     * creates a specific user
     * @param id the specific user you want to view
     * @return json of user
     */
    @POST
    @RolesAllowed({"Admin","APIGroup"})
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Appuser entity) {
        if (entity != null) {
            if (entity.getId() == null) {
                return super.create(entity);
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("ID cannot be supplied on root").build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Error Creating User").build();
    }

     /**
     * modifies user
     * @param id the specific user you want to view
     * @return json of user
     */
    @PUT
    @RolesAllowed({"Admin","APIGroup"})
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Appuser entity) {
        super.edit(entity);
    }

    /**
     * Gets information of a specific user
     * @param id the specific user you want to view
     * @return json of user
     */
    @GET
    @RolesAllowed({"Admin","APIGroup"})
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Long id) {
        return super.find(id);
    }

    /**
     * Finds all bouncers in our database. Calls abstract method
     * @return Json list
     */
    @GET
    @RolesAllowed({"Admin","APIGroup"})
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Override
    public Response findAll() {
        return super.findAll();
    }

    @GET
    @RolesAllowed({"Admin","APIGroup"})
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Appuser> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @RolesAllowed({"Admin","APIGroup"})
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
