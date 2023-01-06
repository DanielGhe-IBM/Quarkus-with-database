package org.project.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.project.model.Author;
import org.project.model.Genre;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GenreRepository implements PanacheRepository<Genre> {



}
