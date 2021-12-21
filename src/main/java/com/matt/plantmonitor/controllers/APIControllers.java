package com.matt.plantmonitor.controllers;

import com.matt.plantmonitor.models.PlantLookup;
import com.matt.plantmonitor.models.Plants;
import com.matt.plantmonitor.models.Readings;
import com.matt.plantmonitor.models.Sensors;
import com.matt.plantmonitor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class APIControllers {

    @Autowired
    private PlantLookupRepository plantLookupRepository;

    @Autowired
    private PlantsRepository plantsRepository;

    @Autowired
    private SensorsRepository sensorsRepository;

    @Autowired
    private ReadingsRepository readingsRepository;

    @GetMapping(path = "/getPlantLookup")
    public @ResponseBody
    Iterable<PlantLookup> getPlantLookup() {
        System.out.println("Getting all plants from plant lookup database table");
        return plantLookupRepository.findAll();
    }

    @GetMapping(path = "/getPlants")
    public @ResponseBody
    Iterable<Plants> getPlants() {
        System.out.println("Getting all plants from plants database table");
        return plantsRepository.findAll();
    }

    @DeleteMapping(path = "/deletePlant/{id}")
    public String deletePlantById(@PathVariable int id) {
        //TODO add service code to check if plant has any sensors and to delete
        //these sensors before deleting the plant
        plantsRepository.deleteById(id);
        return ("Deleted plant with id " + id);
    }

    @PostMapping(path = "/addPlant", consumes = "application/json")
    public @ResponseBody
    String addNewPlant(@RequestBody Plants plants) {
        plantsRepository.save(plants);
        System.out.println("Added plant with the following details: " + plants.toString());
        return "plant added";
    }

    @GetMapping(path = "/getLookupId/PlantLookup/{name}")
    public @ResponseBody
    int getLookupId(@PathVariable String name) {
        System.out.println("Getting lookup ID of plant type " + name);
        return plantLookupRepository.getLookupIdByName(name);
    }

    @GetMapping(path = "/getSensors")
    public @ResponseBody
    Iterable<Sensors> getSensors() {
        System.out.println("Getting all sensors from sensors database table");
        return sensorsRepository.findAll();
    }

    @DeleteMapping(path = "/deleteSensor/{id}")
    public String deleteSensorById(@PathVariable int id) {
        sensorsRepository.deleteById(id);
        return ("Deleted sensor with id " + id);
    }

    @GetMapping(path = "/getLookupId/Plants/{name}")
    public @ResponseBody
    int getPlantsLookupId(@PathVariable String name) {
        System.out.println("Getting lookup ID of plant " + name);
        return plantsRepository.getLookupIdByName(name);
    }

    @PostMapping(path = "/addSensor", consumes = "application/json")
    public @ResponseBody
    String addNewSensor(@RequestBody Sensors sensors) {
        sensorsRepository.save(sensors);
        //System.out.println("Added plant with the following details: " + plants.toString());
        return "sensor added";
    }

    @GetMapping(path = "/getSinglePlant/ById/{id}")
    public @ResponseBody
    Plants getPlantById(@PathVariable String id) {
        System.out.println("Getting details of plant " + id);
        return plantsRepository.getPlantById(id);
    }

    @GetMapping(path = "/getReadings/ByPlantId/{id}")
    public @ResponseBody
    Iterable<Readings> getLatestReadingsByPlantId(@PathVariable String id) {
        //change this to return all latest readings
        System.out.println("Getting readings of plant " + id);
        return readingsRepository.getLatestReadingsById(id);
    }
}