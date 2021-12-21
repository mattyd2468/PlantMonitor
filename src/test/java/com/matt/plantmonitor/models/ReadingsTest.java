package com.matt.plantmonitor.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class ReadingsTest {

    Readings testReadings = new Readings();

    @Test
    void testCreateReading() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        String sdate = sdf.format(date);
        testReadings.setId(1);
        testReadings.setPlantLookupId(2);
        testReadings.setSensorLookupId(3);
        testReadings.setReading(2.03f);
        testReadings.setSensorName("sensor name");
        testReadings.setUnitOfMeasurement("uom");
        testReadings.setTimestamp(sdate);
        Assertions.assertNotNull(testReadings);
        Assertions.assertEquals(1, testReadings.getId());
        Assertions.assertEquals(2, testReadings.getPlantLookupId());
        Assertions.assertEquals(3, testReadings.getSensorLookupId());
        Assertions.assertEquals(2.03f, testReadings.getReading());
        Assertions.assertEquals("sensor name", testReadings.getSensorName());
        Assertions.assertEquals("uom", testReadings.getUnitOfMeasurement());
        Assertions.assertNotNull(testReadings.getTimestamp());
    }

}