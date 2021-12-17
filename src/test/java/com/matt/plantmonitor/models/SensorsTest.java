package com.matt.plantmonitor.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SensorsTest {

    Sensors testSensors = new Sensors();

    @Test
    void testCreateSensors() {
        testSensors.setName("name");
        testSensors.setUnitOfMeasurement("unit of measurement");
        testSensors.setLookupId(2);
        testSensors.setId(1);
        Assertions.assertNotNull(testSensors);
        Assertions.assertEquals("name", testSensors.getName());
        Assertions.assertEquals("unit of measurement", testSensors.getUnitOfMeasurement());
        Assertions.assertEquals(2, testSensors.getLookupId());
        Assertions.assertEquals(1, testSensors.getId());
    }
}