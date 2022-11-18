package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity             //Added this, so that the Employer class can be mapped to the SQL tables.
public class Employer extends AbstractEntity {

    @NotBlank           //Added this, so user cannot leave field blank
    @Size(max = 50)     //Added this as reasonable limitations on size of location string
    private String location;

    public Employer() { //Added this so Hibernate can create an object
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() { //Added this public getter so the information of the private location field can be accessed
        return location;
    }
}
