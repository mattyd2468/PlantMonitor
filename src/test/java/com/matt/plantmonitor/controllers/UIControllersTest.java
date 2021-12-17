package com.matt.plantmonitor.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.ui.Model;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UIControllersTest {
    @Mock
    Model mockModel;

    UIControllers testUIController = new UIControllers();

    @Test
    void homePage() {
        when(mockModel.addAttribute(any(),any())).thenReturn(mockModel);
        Assertions.assertEquals("home", testUIController.homePage(mockModel));
    }

    @Test
    void alsoHomePage() {
        when(mockModel.addAttribute(any(),any())).thenReturn(mockModel);
        Assertions.assertEquals("home", testUIController.alsoHomePage(mockModel));
    }

    @Test
    void addRemovePlant() {
        when(mockModel.addAttribute(any(),any())).thenReturn(mockModel);
        Assertions.assertEquals("addRemovePlant", testUIController.addRemovePlant(mockModel));
    }

    @Test
    void addRemoveSensor() {
        when(mockModel.addAttribute(any(),any())).thenReturn(mockModel);
        Assertions.assertEquals("addRemoveSensor", testUIController.addRemoveSensor(mockModel));
    }

    @Test
    void assignSensor() {
        when(mockModel.addAttribute(any(),any())).thenReturn(mockModel);
        Assertions.assertEquals("assignSensor", testUIController.assignSensor(mockModel));
    }
}