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
        System.out.println("Getting all plants from plant lookup database table");
        return plantLookupRepository.findAll();
    }

    @GetMapping(path="/getPlants")
    public @ResponseBody
    Iterable<Plants> getPlants() {
        System.out.println("Getting all plants from plants database table");
        return plantsRepository.findAll();
    }

    @DeleteMapping(path="/deletePlant/{id}")
    public String deletePlantById(@PathVariable int id) {
        plantsRepository.deleteById(id);
        return("Deleted plant with id " + id);
    }

    @PostMapping(path="/addPlant", consumes="application/json")
    public @ResponseBody String addNewPlant (@RequestBody Plants plants) {
        // @ResponseBody means the returned String is the response, not a view name
        System.out.println(plants.toString());// @RequestParam means it is a parameter from the GET or POST request
        plantsRepository.save(plants);
        System.out.println("Added plant with the following details: " + plants.toString());
        return "plant added";
    }

    @GetMapping(path="/getLookupId/{name}")
    public @ResponseBody
    int getLookupId(@PathVariable String name) {
        System.out.println("Getting lookup ID of plant type " + name);
        return plantLookupRepository.getLookupIdByName(name);
    }

}