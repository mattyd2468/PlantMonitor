package com.matt.plantmonitor.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static  org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlantLookupTests {

    PlantLookup testPlantLookup = new PlantLookup();

    @Test
    void testCreatePlantLookup() {
        testPlantLookup.setName("name");
        testPlantLookup.setDescription("description");
        testPlantLookup.setLatin("latin");
        testPlantLookup.setPhoto("photo");
        testPlantLookup.setId(1);
        Assertions.assertNotNull(testPlantLookup);
        Assertions.assertEquals("name", testPlantLookup.getName());
        Assertions.assertEquals("description", testPlantLookup.getDescription());
        Assertions.assertEquals("latin", testPlantLookup.getLatin());
        Assertions.assertEquals("photo", testPlantLookup.getPhoto());
        Assertions.assertEquals(1, testPlantLookup.getId());
    }

}