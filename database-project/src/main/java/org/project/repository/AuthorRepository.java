package org.project.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.project.model.Author;
import javax.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class AuthorRepository implements PanacheRepository<Author> {



}
