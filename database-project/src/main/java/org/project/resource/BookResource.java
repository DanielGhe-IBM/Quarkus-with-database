package org.project.resource;


import org.project.model.Book;
import org.project.repository.BookRepository;

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

@Path("/books")
public class BookResource{
    private BookRepository bookRepository;
    @Inject
    public void BookRepository (BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(bookRepository.listAll()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id){
       return bookRepository.findByIdOptional(id).map(book ->Response.ok(book).build())
                .orElse(Response.status(NOT_FOUND).build());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(Book book) {
        bookRepository.persist(book);
        return Response.created(URI.create("/" + book.getId())).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(Book book) {
        Book toUpdate = bookRepository.findById(book.id);
        toUpdate.name = book.name;
        toUpdate.description = book.description;
        toUpdate.yearOfPublication = book.yearOfPublication;
        return Response.ok(book).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteById(@PathParam("id") Long id) {
        boolean deleted = bookRepository.deleteById(id);
        return deleted ? Response.noContent().build() :
                Response.status(Response.Status.BAD_REQUEST).build();
    }

}