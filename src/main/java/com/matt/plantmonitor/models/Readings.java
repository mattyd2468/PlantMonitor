package com.matt.plantmonitor.models;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Table(name = "readings")
@Entity
public class Readings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int plantLookupId;

    private int sensorLookupId;

    private float reading;

    private String timestamp;

    //TODO - Set fields sensorName and unitOfMeasurement as transient
    private String sensorName;

    private String unitOfMeasurement;

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

    public float getReading() {
        return reading;
    }

    public void setReading(float reading) {
        this.reading = reading;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }
}
