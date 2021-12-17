package com.matt.plantmonitor.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIControllers {
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @GetMapping("/home")
    public String alsoHomePage(Model model) {
        model.addAttribute("appName", appName);
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

}