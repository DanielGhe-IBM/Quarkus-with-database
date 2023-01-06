package org.project.resource;


import org.project.model.Author;
import org.project.repository.AuthorRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/authors")
public class AuthorResource{

    private AuthorRepository authorRepository;
    @Inject
    public void AuthorRepository (AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(authorRepository.listAll()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id){
        return authorRepository.findByIdOptional(id).map(author ->Response.ok(author).build())
                .orElse(Response.status(NOT_FOUND).build());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(Author author) {
        authorRepository.persist(author);
        return Response.created(URI.create("/" + author.getId())).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(Author author) {
        Author toUpdate = authorRepository.findById(author.id);
        toUpdate.name = author.name;
        toUpdate.bio = author.bio;
        toUpdate.age = author.age;
        return Response.ok(author).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteById(@PathParam("id") Long id) {
        boolean deleted = authorRepository.deleteById(id);
        return deleted ? Response.noContent().build() :
                Response.status(Response.Status.BAD_REQUEST).build();
    }

}