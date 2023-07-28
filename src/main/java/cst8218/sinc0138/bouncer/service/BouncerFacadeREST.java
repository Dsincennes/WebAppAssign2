/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.sinc0138.bouncer.service;

import cst8218.sinc0138.bouncer.entity.Bouncer;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonNumber;
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
import javax.ws.rs.core.Response;

/**
 * RESTful API class, all my endpoints are created here for CRUD operations on the database.
 * @author Donald
 */
@Stateless
@Path("bouncer")
public class BouncerFacadeREST extends AbstractFacade<Bouncer> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public BouncerFacadeREST() {
        super(Bouncer.class);
    }

    /**
     * Post on the root, Creates a new Bouncer if no ID is provided. 
     * @param entity Body of the bouncer
     * @return Response based on outcome
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Response create(Bouncer entity) {
        if (entity != null) {
            if (entity.getId() == null) {
                return super.create(entity);
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("ID cannot be supplied on root").build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Error Creating Bouncer").build();
    }
    
    /**
     * Post with an ID path, Will update a bouncer with a given ID.
     * @param id specific bouncer
     * @param entity body of bouncer
     * @return response
     */
    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createWithId(@PathParam("id") Long id, Bouncer entity) {
        if (entity != null) {
            if (entity.getId() == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("id is null").build();
            }

            Response oldBouncerResponse = super.find(entity.getId());

            if (oldBouncerResponse.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
                return Response.status(Response.Status.NOT_FOUND).entity("Bouncer with id: " + id + " Not Found").build();
            }

            Bouncer oldBouncer = (Bouncer) oldBouncerResponse.getEntity();
            entity.updateBouncer(oldBouncer);
            return super.edit(entity);
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Error Creating Bouncer").build();
    }

    /**
     * Completely replaces a specific bouncer with given body values. Wont allow
     * if bouncer doesn't exist.
     * @param id id of bouncer to edit
     * @param entity body of request
     * @return response REview out of range as well***
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Long id, Bouncer entity) {
        if (entity.getId() == null
                || entity.getX() == null
                || entity.getY() == null
                || entity.getySpeed() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad Request").build();
        }
        Response oldBouncerResponse = super.find(entity.getId());

        if (oldBouncerResponse.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Bouncer with id: " + id + " Not Found").build();
        }
        return super.edit(entity);
    }

    /**
     * Gets information of a specific bouncer
     * @param id the specific bouncer you want to view
     * @return json of bouncer
     */
    @GET
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
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Override
    public Response findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Bouncer> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    /**
     * Gets total number of bouncers in JSON format. Calls Abstract Facade count() method
     * 
     * @return JsonNumber size of Json list
     */
    @GET
    @Path("count")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonNumber countREST() {
        return Json.createValue(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
