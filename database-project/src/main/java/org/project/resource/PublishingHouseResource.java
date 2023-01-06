package org.project.resource;


import org.project.model.PublishingHouse;
import org.project.repository.PublishingHouseRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/publishingHouses")
public class PublishingHouseResource {
    private PublishingHouseRepository publishingHouseRepository;

    @Inject
    public void PublishingHouseRepository(PublishingHouseRepository publishingHouseRepository) {
        this.publishingHouseRepository = publishingHouseRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(publishingHouseRepository.listAll()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        return publishingHouseRepository.findByIdOptional(id).map(publishingHouse -> Response.ok(publishingHouse).build())
                .orElse(Response.status(NOT_FOUND).build());
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(PublishingHouse publishingHouse) {
        publishingHouseRepository.persist(publishingHouse);
        return Response.created(URI.create("/" + publishingHouse.getId())).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(PublishingHouse publishingHouse) {
        PublishingHouse toUpdate = publishingHouseRepository.findById(publishingHouse.id);
        toUpdate.name = publishingHouse.name;
        toUpdate.description = publishingHouse.description;
        return Response.ok(publishingHouse).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteById(@PathParam("id") Long id) {
        boolean deleted = publishingHouseRepository.deleteById(id);
        return deleted ? Response.noContent().build() :
                Response.status(Response.Status.BAD_REQUEST).build();
    }

}