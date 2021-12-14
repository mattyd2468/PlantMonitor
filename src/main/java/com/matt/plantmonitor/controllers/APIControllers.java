package com.matt.plantmonitor.controllers;

import com.matt.plantmonitor.models.PlantLookup;
import com.matt.plantmonitor.models.Plants;
import com.matt.plantmonitor.repository.PlantLookupRepository;
import com.matt.plantmonitor.repository.PlantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class APIControllers {

    @Autowired
    private PlantLookupRepository plantLookupRepository;

    @Autowired
    private PlantsRepository plantsRepository;

    @GetMapping(path="/getPlantLookup")
    public @ResponseBody
    Iterable<PlantLookup> getPlantLookup() {
        System.out.println("Getting all plants from plant lookup");
        // This returns a JSON or XML with all plants in table plant_lookup
        return plantLookupRepository.findAll();
    }

    @GetMapping(path="/getPlants")
    public @ResponseBody
    Iterable<Plants> getPlants() {
        System.out.println("Getting all plants from plants");
        // This returns a JSON or XML with all plants in table plant_lookup
        return plantsRepository.findAll();
    }

    @DeleteMapping(path="/deletePlant/{id}")
    public void deletePlantById(@PathVariable int id) {
        plantsRepository.deleteById(id);
        System.out.println("Deleted plant with id " + id);
    }

}