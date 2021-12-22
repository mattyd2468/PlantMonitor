package com.matt.plantmonitor.models;

import javax.persistence.*;

@Table(name = "acceptable_range")
@Entity
public class AcceptableRange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int plantLookupId;

    private int sensorLookupId;

    //TODO - Update database to remove typo in Boundry (Boundary)
    private float upperBoundry;

    private float lowerBoundry;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPlantLookupId() {
        return plantLookupId;
    }

    public void setPlantLookupId(int plantLookupId) {
        this.plantLookupId = plantLookupId;
    }

    public int getSensorLookupId() {
        return sensorLookupId;
    }

    public void setSensorLookupId(int sensorLookupId) {
        this.sensorLookupId = sensorLookupId;
    }

    public float getUpperBoundry() {
        return upperBoundry;
    }

    public void setUpperBoundry(float upperBoundry) {
        this.upperBoundry = upperBoundry;
    }

    public float getLowerBoundry() {
        return lowerBoundry;
    }

    public void setLowerBoundry(float lowerBoundry) {
        this.lowerBoundry = lowerBoundry;
    }
}
