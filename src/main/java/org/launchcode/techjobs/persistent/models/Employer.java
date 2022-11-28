package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity             //Added this, so that the Employer class can be mapped to the SQL tables.
public class Employer extends AbstractEntity {

    @NotBlank           //Added this, so user cannot leave field blank
    @Size(min =3, max = 100)     //Added this as reasonable limitations on size of location string
    private String location;

    @OneToMany
    @JoinColumn(name = "employer_id")  //this is throwing a Hibernate error and the code won't compile
    private final List<Job> jobs = new ArrayList<>();

    public Employer() { //Added this so Hibernate can create an object
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() { //Added this public getter so the information of the private location field can be accessed
        return location;
    }

    public List<Job> getJobs() {
        return jobs;
    }
}
