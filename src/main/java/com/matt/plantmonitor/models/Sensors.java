package com.matt.plantmonitor.models;

import javax.persistence.*;

@Table(name = "sensors")
@Entity
public class Sensors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int lookupId;

    private String name;

    private String unitOfMeasurement;

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

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }
}
