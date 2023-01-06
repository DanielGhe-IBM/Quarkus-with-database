package org.project.resource;


import org.project.model.Genre;
import org.project.repository.GenreRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/genres")
public class GenreResource{
    private GenreRepository genreRepository;
    @Inject
    public void GenreRepository (GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(genreRepository.listAll()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id){
        return genreRepository.findByIdOptional(id).map(genre ->Response.ok(genre).build())
                .orElse(Response.status(NOT_FOUND).build());
    }



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(Genre genre) {
        genreRepository.persist(genre);
        return Response.created(URI.create("/" + genre.getId())).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(Genre genre) {
        Genre toUpdate = genreRepository.findById(genre.id);
        toUpdate.name = genre.name;
        toUpdate.description = genre.description;
        return Response.ok(genre).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteById(@PathParam("id") Long id) {
        boolean deleted = genreRepository.deleteById(id);
        return deleted ? Response.noContent().build() :
                Response.status(Response.Status.BAD_REQUEST).build();
    }

}