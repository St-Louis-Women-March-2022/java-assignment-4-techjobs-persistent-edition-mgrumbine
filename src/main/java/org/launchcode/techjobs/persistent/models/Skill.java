package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
public class Skill extends AbstractEntity {

    @Size(max = 255)    //Added this because otherwise too long descriptions get white label error. not pretty for users.
    private String description;

    public Skill() { //Added this so Hibernate can create an object
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}