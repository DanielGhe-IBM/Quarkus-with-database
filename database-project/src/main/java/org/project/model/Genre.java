package org.project.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;


@Entity
    @Transactional
    @Table(name="genres")
    public class Genre extends PanacheEntityBase {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        public Long id;
        public String name;
        public String description;
        @OneToOne
        public Book book;
        @ManyToMany(targetEntity = Author.class)
        public List<Author> authors;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Book getBook() {
            return book;
        }

        public void setBook(Book book) {
            this.book = book;
        }
    }