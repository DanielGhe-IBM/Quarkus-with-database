package org.project.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.transaction.Transactional;

    @Entity
    @Transactional
    @Table(name="publishing_houses")
    public class PublishingHouse extends PanacheEntityBase {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        public Long id;
        public String name;
        public String description;
        public int foundingYear;

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

        public int getFoundingYear() {
            return foundingYear;
        }

        public void setFoundingYear(int foundingYear) {
            this.foundingYear = foundingYear;
        }


}
