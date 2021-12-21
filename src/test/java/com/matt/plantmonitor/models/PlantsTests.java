package com.matt.plantmonitor.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlantsTests {

    Plants testPlants = new Plants();

    @Test
    void testCreatePlant() {
        testPlants.setName("name");
        testPlants.setLocation("location");
        testPlants.setLookupId(2);
        testPlants.setStatus("status");
        testPlants.setId(1);
        Assertions.assertNotNull(testPlants);
        Assertions.assertEquals("name", testPlants.getName());
        Assertions.assertEquals("location", testPlants.getLocation());
        Assertions.assertEquals(2, testPlants.getLookupId());
        Assertions.assertEquals("status", testPlants.getStatus());
        Assertions.assertEquals(1, testPlants.getId());
    }

    @Test
    void testPlantsToString() {
        testPlants.setName("name");
        testPlants.setLocation("location");
        testPlants.setLookupId(2);
        testPlants.setStatus("status");
        testPlants.setId(1);
        String expected = "ID = 1 LookupID = 2 Name = name Location = location Status = status";
        Assertions.assertNotNull(testPlants);
        Assertions.assertEquals(expected, testPlants.toString());

    }

}