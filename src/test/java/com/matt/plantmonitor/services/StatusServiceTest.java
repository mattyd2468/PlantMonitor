package com.matt.plantmonitor.services;

import com.matt.plantmonitor.models.AcceptableRange;
import com.matt.plantmonitor.models.Plants;
import com.matt.plantmonitor.models.Readings;
import com.matt.plantmonitor.models.Sensors;
import com.matt.plantmonitor.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StatusServiceTest {

    @MockBean
    private PlantLookupRepository plantLookupRepository;

    @MockBean
    private PlantsRepository plantsRepository;

    @MockBean
    private SensorsRepository sensorsRepository;

   @MockBean
    private ReadingsRepository readingsRepository;

    @MockBean
    private AcceptableRangeRepository acceptableRangeRepository;

    @InjectMocks
    StatusService testStatusService = new StatusService();

    Plants testPlants1 = new Plants();
    Sensors testSensors1 = new Sensors();
    Sensors testSensors2 = new Sensors();
    Sensors testSensors3 = new Sensors();
    Sensors testSensors4 = new Sensors();
    Readings testReadings1 = new Readings();
    Readings testReadings2 = new Readings();
    Readings testReadings3 = new Readings();
    Readings testReadings4 = new Readings();
    AcceptableRange testAcceptableRange1 = new AcceptableRange();
    AcceptableRange testAcceptableRange2 = new AcceptableRange();
    AcceptableRange testAcceptableRange3 = new AcceptableRange();
    AcceptableRange testAcceptableRange4 = new AcceptableRange();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        //test plant object
        testPlants1.setName("Plant Name");
        testPlants1.setLocation("Plant Location");
        testPlants1.setLookupId(2);
        testPlants1.setStatus(null);
        testPlants1.setId(1);

        //test humidity sensor
        testSensors1.setName("humidity sensor");
        testSensors1.setUnitOfMeasurement("%");
        testSensors1.setLookupId(1);
        testSensors1.setId(1);
        //test temp sensor
        testSensors2.setName("temp sensor");
        testSensors2.setUnitOfMeasurement("degrees");
        testSensors2.setLookupId(1);
        testSensors2.setId(2);
        //test moisture sensor
        testSensors3.setName("moisture sensor");
        testSensors3.setUnitOfMeasurement("%");
        testSensors3.setLookupId(1);
        testSensors3.setId(3);
        //test ph monitor
        testSensors4.setName("ph sensor");
        testSensors4.setUnitOfMeasurement("pH");
        testSensors4.setLookupId(1);
        testSensors4.setId(4);

        //sensor 1 readings
        testReadings1.setId(1);
        testReadings1.setPlantLookupId(1);
        testReadings1.setSensorLookupId(1);
        testReadings1.setReading(25);
        testReadings1.setSensorName("humidity sensor");
        testReadings1.setUnitOfMeasurement("%");
        testReadings1.setTimestamp("Tue, 21 Dec 2021 17:01:39");
        //sensor 2 readings
        testReadings2.setId(2);
        testReadings2.setPlantLookupId(1);
        testReadings2.setSensorLookupId(2);
        testReadings2.setReading(25);
        testReadings2.setSensorName("temp sensor");
        testReadings2.setUnitOfMeasurement("degrees");
        testReadings2.setTimestamp("Tue, 21 Dec 2021 17:01:38");
        //sensor 3 readings
        testReadings3.setId(3);
        testReadings3.setPlantLookupId(1);
        testReadings3.setSensorLookupId(3);
        testReadings3.setReading(75);
        testReadings3.setSensorName("moisture sensor");
        testReadings3.setUnitOfMeasurement("%");
        testReadings3.setTimestamp("Tue, 21 Dec 2021 17:01:36");
        //sensor 4 readings
        testReadings4.setId(4);
        testReadings4.setPlantLookupId(1);
        testReadings4.setSensorLookupId(4);
        testReadings4.setReading(8);
        testReadings4.setSensorName("ph sensor");
        testReadings4.setUnitOfMeasurement("ph");
        testReadings4.setTimestamp("Tue, 21 Dec 2021 17:01:33");

        //sensor 1 range
        testAcceptableRange1.setId(1);
        testAcceptableRange1.setPlantLookupId(1);
        testAcceptableRange1.setSensorLookupId(1);
        testAcceptableRange1.setLowerBoundry(20);
        testAcceptableRange1.setUpperBoundry(30);
        //sensor 2 range
        testAcceptableRange2.setId(2);
        testAcceptableRange2.setPlantLookupId(1);
        testAcceptableRange2.setSensorLookupId(2);
        testAcceptableRange2.setLowerBoundry(20);
        testAcceptableRange2.setUpperBoundry(30);
        //sensor 3 range
        testAcceptableRange3.setId(3);
        testAcceptableRange3.setPlantLookupId(1);
        testAcceptableRange3.setSensorLookupId(23);
        testAcceptableRange3.setLowerBoundry(50);
        testAcceptableRange3.setUpperBoundry(90);
        //sensor 4 range
        testAcceptableRange4.setId(4);
        testAcceptableRange4.setPlantLookupId(1);
        testAcceptableRange4.setSensorLookupId(4);
        testAcceptableRange4.setLowerBoundry(6);
        testAcceptableRange4.setUpperBoundry(9);

    }

    @Test
    void updateStatus_green() {
        testPlants1.setStatus("Green");

        List<Readings> readings = new ArrayList<>(Arrays.asList(testReadings1, testReadings2, testReadings3, testReadings4));
        Mockito.when(readingsRepository.getLatestReadingsById("1")).thenReturn(readings);
        Mockito.when(acceptableRangeRepository.getAcceptableRangeBySensorId("1")).thenReturn(testAcceptableRange1);
        Mockito.when(acceptableRangeRepository.getAcceptableRangeBySensorId("2")).thenReturn(testAcceptableRange2);
        Mockito.when(acceptableRangeRepository.getAcceptableRangeBySensorId("3")).thenReturn(testAcceptableRange3);
        Mockito.when(acceptableRangeRepository.getAcceptableRangeBySensorId("4")).thenReturn(testAcceptableRange4);

        Assertions.assertEquals(testPlants1.getStatus(), testStatusService.updateStatus(testPlants1).getStatus());

    }

    @Test
    void updateStatus_amber() {
        testPlants1.setStatus("Amber");
        testReadings1.setReading(100);

        List<Readings> readings = new ArrayList<>(Arrays.asList(testReadings1, testReadings2, testReadings3, testReadings4));
        Mockito.when(readingsRepository.getLatestReadingsById("1")).thenReturn(readings);
        Mockito.when(acceptableRangeRepository.getAcceptableRangeBySensorId("1")).thenReturn(testAcceptableRange1);
        Mockito.when(acceptableRangeRepository.getAcceptableRangeBySensorId("2")).thenReturn(testAcceptableRange2);
        Mockito.when(acceptableRangeRepository.getAcceptableRangeBySensorId("3")).thenReturn(testAcceptableRange3);
        Mockito.when(acceptableRangeRepository.getAcceptableRangeBySensorId("4")).thenReturn(testAcceptableRange4);

        Assertions.assertEquals(testPlants1.getStatus(), testStatusService.updateStatus(testPlants1).getStatus());

    }

    @Test
    void updateStatus_red() {
        testPlants1.setStatus("Red");
        testReadings1.setReading(100);
        testReadings2.setReading(100);
        testReadings3.setReading(100);
        testReadings4.setReading(100);

        List<Readings> readings = new ArrayList<>(Arrays.asList(testReadings1, testReadings2, testReadings3, testReadings4));
        Mockito.when(readingsRepository.getLatestReadingsById("1")).thenReturn(readings);
        Mockito.when(acceptableRangeRepository.getAcceptableRangeBySensorId("1")).thenReturn(testAcceptableRange1);
        Mockito.when(acceptableRangeRepository.getAcceptableRangeBySensorId("2")).thenReturn(testAcceptableRange2);
        Mockito.when(acceptableRangeRepository.getAcceptableRangeBySensorId("3")).thenReturn(testAcceptableRange3);
        Mockito.when(acceptableRangeRepository.getAcceptableRangeBySensorId("4")).thenReturn(testAcceptableRange4);

        Assertions.assertEquals(testPlants1.getStatus(), testStatusService.updateStatus(testPlants1).getStatus());

    }
}