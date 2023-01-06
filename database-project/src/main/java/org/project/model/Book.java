package org.project.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Example JPA entity.
 *
 * To use it, get access to a JPA EntityManager via injection.
 *
 * {@code
 *     @Inject
 *     EntityManager em;
 *
 *     public void doSomething() {
 *         MyEntity entity1 = new MyEntity();
 *         entity1.setField("field-1");
 *         em.persist(entity1);
 *
 *         List<MyEntity> entities = em.createQuery("from MyEntity", MyEntity.class).getResultList();
 *     }
 * }
 */
@Entity
@Transactional
@Table(name="books")
public class Book extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String name;
    public String description;
    public int yearOfPublication;
    @OneToMany(targetEntity = PublishingHouse.class)
    public List publishingHouses;



    public Book() {
    }

    public Book(String name, String description, int yearOfPublication) {
        this.name = name;
        this.description = description;
        this.yearOfPublication = yearOfPublication;
    }

    public List getPublishingHouses() {
        return publishingHouses;
    }

    public void setPublishingHouses(List publishingHouses) {
        this.publishingHouses = publishingHouses;
    }

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

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }
}
