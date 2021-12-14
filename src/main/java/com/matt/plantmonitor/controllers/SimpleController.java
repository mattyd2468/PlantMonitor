/*
package com.matt.plantmonitor.controllers;

import com.matt.plantmonitor.models.PlantLookup;
import com.matt.plantmonitor.repository.PlantLookupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {
    @Value("${spring.application.name}")
    String appName;

    @Autowired    private PlantLookupRepository plantLookupRepository;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        //jdbc.execute("insert into plantmonitordb.plantlookup values(2,'money plant', null, 'description')");
        return "home";
    }

    @GetMapping("/addRemovePlant")
    public String addRemovePlant(Model model) {
        model.addAttribute("appName", appName);
        return "addRemovePlant";
    }

    @GetMapping("/addRemoveSensor")
    public String addRemoveSensor(Model model) {
        model.addAttribute("appName", appName);
        return "addRemoveSensor";
    }

    @GetMapping("/assignSensor")
    public String assignSensor(Model model) {
        model.addAttribute("appName", appName);
        return "assignSensor";
    }

    @GetMapping(path="/getPlantLookup")
    public @ResponseBody
    Iterable<PlantLookup> getAllPlants() {
        // This returns a JSON or XML with all plants in table plant_lookup
        return plantLookupRepository.findAll();
    }

}*/
