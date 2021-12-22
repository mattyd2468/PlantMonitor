package com.matt.plantmonitor.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AcceptableRangeTest {

    AcceptableRange testAcceptableRange = new AcceptableRange();

    @Test
    void testCreatePlantLookup() {
        testAcceptableRange.setId(1);
        testAcceptableRange.setPlantLookupId(2);
        testAcceptableRange.setSensorLookupId(3);
        testAcceptableRange.setLowerBoundry(5);
        testAcceptableRange.setUpperBoundry(10);
        Assertions.assertNotNull(testAcceptableRange);
        Assertions.assertEquals(1, testAcceptableRange.getId());
        Assertions.assertEquals(2, testAcceptableRange.getPlantLookupId());
        Assertions.assertEquals(3, testAcceptableRange.getSensorLookupId());
        Assertions.assertEquals(5, testAcceptableRange.getLowerBoundry());
        Assertions.assertEquals(10, testAcceptableRange.getUpperBoundry());
    }

}