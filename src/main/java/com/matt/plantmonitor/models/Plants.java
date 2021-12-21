package com.matt.plantmonitor.models;

import javax.persistence.*;

@Table(name = "plants")
@Entity
public class Plants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int lookupId;

    private String name;

    private String location;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getLookupId() {
        return lookupId;
    }

    public void setLookupId(int lookupId) {
        this.lookupId = lookupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {

        return (String.format("ID = %s LookupID = %s Name = %s Location = %s Status = %s", id, lookupId, name, location, status));
    }
}
