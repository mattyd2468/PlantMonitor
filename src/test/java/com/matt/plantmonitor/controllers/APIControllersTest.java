package com.matt.plantmonitor.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matt.plantmonitor.models.*;
import com.matt.plantmonitor.repository.*;
import com.matt.plantmonitor.services.StatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebMvcTest(APIControllers.class)
public class APIControllersTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

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

    @MockBean
    StatusService statusService;

    PlantLookup testPlantLookup1 = new PlantLookup();
    PlantLookup testPlantLookup2 = new PlantLookup();
    Plants testPlants1 = new Plants();
    Plants testPlants2 = new Plants();
    Sensors testSensors1 = new Sensors();
    Sensors testSensors2 = new Sensors();
    Sensors testSensors3 = new Sensors();
    Sensors testSensors4 = new Sensors();
    Readings testReadings1 = new Readings();
    Readings testReadings2 = new Readings();
    AcceptableRange testAcceptableRange1 = new AcceptableRange();
    AcceptableRange testAcceptableRange2 = new AcceptableRange();

    @BeforeEach
    public void setUp() {
        testPlantLookup1.setName("name");
        testPlantLookup1.setDescription("description");
        testPlantLookup1.setLatin("latin");
        testPlantLookup1.setPhoto("photo");
        testPlantLookup1.setId(1);
        testPlantLookup2.setName("name2");
        testPlantLookup2.setDescription("description2");
        testPlantLookup2.setLatin("latin2");
        testPlantLookup2.setPhoto("photo2");
        testPlantLookup2.setId(2);

        testPlants1.setName("name");
        testPlants1.setLocation("location");
        testPlants1.setLookupId(2);
        testPlants1.setStatus("status");
        testPlants1.setId(1);
        testPlants2.setName("name2");
        testPlants2.setLocation("location2");
        testPlants2.setLookupId(2);
        testPlants2.setStatus("status2");
        testPlants2.setId(2);

        testSensors1.setName("name");
        testSensors1.setUnitOfMeasurement("unit of measurement");
        testSensors1.setLookupId(2);
        testSensors1.setId(1);
        testSensors2.setName("name2");
        testSensors2.setUnitOfMeasurement("unit of measurement");
        testSensors2.setLookupId(2);
        testSensors2.setId(2);
        testSensors3.setName("name3");
        testSensors3.setUnitOfMeasurement("unit of measurement");
        testSensors3.setLookupId(2);
        testSensors3.setId(3);
        testSensors4.setName("name4");
        testSensors4.setUnitOfMeasurement("unit of measurement");
        testSensors4.setLookupId(2);
        testSensors4.setId(4);

        //readings for plant 1
        testReadings1.setId(1);
        testReadings1.setPlantLookupId(1);
        testReadings1.setSensorLookupId(3);
        testReadings1.setReading(2.03f);
        testReadings1.setSensorName("sensor name");
        testReadings1.setUnitOfMeasurement("uom");
        testReadings1.setTimestamp("Tue, 21 Dec 2021 17:01:39");
        testReadings2.setId(2);
        testReadings2.setPlantLookupId(1);
        testReadings2.setSensorLookupId(4);
        testReadings2.setReading(2.03f);
        testReadings2.setSensorName("sensor name2");
        testReadings2.setUnitOfMeasurement("uom");
        testReadings2.setTimestamp("Tue, 21 Dec 2021 17:01:39");

        //reading1 is in bounds
        testReadings1.setId(3);
        testReadings1.setPlantLookupId(2);
        testReadings1.setSensorLookupId(3);
        testReadings1.setReading(2.03f);
        testReadings1.setSensorName("sensor name");
        testReadings1.setUnitOfMeasurement("uom");
        testReadings1.setTimestamp("Tue, 21 Dec 2021 17:01:39");
        //reading 2 out of bounds
        testReadings2.setId(4);
        testReadings2.setPlantLookupId(2);
        testReadings2.setSensorLookupId(4);
        testReadings2.setReading(2.03f);
        testReadings2.setSensorName("sensor name2");
        testReadings2.setUnitOfMeasurement("uom");
        testReadings2.setTimestamp("Tue, 21 Dec 2021 17:01:39");

        //sensor 3 + 4
        testAcceptableRange1.setId(1);
        testAcceptableRange1.setPlantLookupId(1);
        testAcceptableRange1.setSensorLookupId(3);
        testAcceptableRange1.setLowerBoundry(1);
        testAcceptableRange1.setUpperBoundry(10);
        testAcceptableRange2.setId(2);
        testAcceptableRange2.setPlantLookupId(2);
        testAcceptableRange2.setSensorLookupId(4);
        testAcceptableRange2.setLowerBoundry(3);
        testAcceptableRange2.setUpperBoundry(13);

    }

    @Test
    public void getPlantLookup_success() throws Exception {
        List<PlantLookup> plantLookups = new ArrayList<>(Arrays.asList(testPlantLookup1, testPlantLookup2));
        Mockito.when(plantLookupRepository.findAll()).thenReturn(plantLookups);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/getPlantLookup")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].name", is("name2")));
    }

    @Test
    public void getPlants_success() throws Exception {
        List<Plants> plants = new ArrayList<>(Arrays.asList(testPlants1, testPlants2));
        Mockito.when(plantsRepository.findAll()).thenReturn(plants);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/getPlants")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].name", is("name2")));
    }

    @Test
    public void getSensors_success() throws Exception {
        List<Sensors> sensors = new ArrayList<>(Arrays.asList(testSensors1, testSensors2));
        Mockito.when(sensorsRepository.findAll()).thenReturn(sensors);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/getSensors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].name", is("name2")));
    }

    @Test
    public void getPlantById_success() throws Exception {
        Mockito.when(plantsRepository.getPlantById("1")).thenReturn(testPlants1);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/getSinglePlant/ById/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("name")));
    }

    @Test
    public void addNewPlant_success() throws Exception {
        Mockito.when(plantsRepository.save(testPlants1)).thenReturn(testPlants1);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/addPlant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(testPlants1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", is("plant added")));
    }

    @Test
    public void addNewSensor_success() throws Exception {
        Mockito.when(sensorsRepository.save(testSensors2)).thenReturn(testSensors2);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/addSensor")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(testSensors2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", is("sensor added")));
    }

    @Test
    public void deletePlantById_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/deletePlant/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", is("Deleted plant with id 1")));
    }

    @Test
    public void deleteSensorById_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/deleteSensor/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", is("Deleted sensor with id 2")));
    }

    @Test
    public void getPlantsLookupId_success() throws Exception {
        Mockito.when(plantsRepository.getLookupIdByName("name2")).thenReturn(testPlants2.getLookupId());
        mockMvc.perform(MockMvcRequestBuilders
                .get("/getLookupId/Plants/name2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", is(2)));
    }

    @Test
    public void getLatestReadingsByPlantId_success() throws Exception {
        List<Readings> readings = new ArrayList<>(Arrays.asList(testReadings1, testReadings2));
        Mockito.when(readingsRepository.getLatestReadingsById("2")).thenReturn(readings);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/getReadings/ByPlantId/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$[1].sensorName", is("sensor name2")));
    }

    @Test
    public void getLookupId_success() throws Exception {
        Mockito.when(plantLookupRepository.getLookupIdByName("name")).thenReturn(testPlantLookup1.getId());
        mockMvc.perform(MockMvcRequestBuilders
                .get("/getLookupId/PlantLookup/name")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", is(1)));
    }

    @Test
    public void getAcceptableRangeBySensorId_success() throws Exception {
        Mockito.when(acceptableRangeRepository.getAcceptableRangeBySensorId("3")).thenReturn(testAcceptableRange1);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/getAcceptableBounds/singleSensor/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.upperBoundry", is(10.0)));
    }

    @Test
    public void updateStatus_success() throws Exception {
        List<Plants> plants = new ArrayList<>(Arrays.asList(testPlants1, testPlants2));
        Mockito.when(plantsRepository.findAll()).thenReturn(plants);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/updateStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(testPlants1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", is( "All plant status' updated")));
    }

    @Test
    public void updateSingleStatus_success() throws Exception {
        /*List<Readings> readings = new ArrayList<>(Arrays.asList(testReadings1, testReadings2));
        Mockito.when(readingsRepository.getLatestReadingsById(String.valueOf(testPlants1.getId()))).thenReturn(readings);
        Mockito.when(acceptableRangeRepository.getAcceptableRangeBySensorId(String.valueOf(testReadings1.getSensorLookupId()))).thenReturn(testAcceptableRange1);
        Mockito.when(acceptableRangeRepository.getAcceptableRangeBySensorId(String.valueOf(testReadings2.getSensorLookupId()))).thenReturn(testAcceptableRange2);
        Mockito.when(plantsRepository.save(Mockito.any())).thenReturn(testPlants1);
        testPlants1.setStatus("Amber");*/
            mockMvc.perform(MockMvcRequestBuilders
                    .put("/updateSingleStatus")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(this.mapper.writeValueAsString(testPlants1)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", notNullValue()))
                    .andExpect(jsonPath("$", is( "Status updated with details: " + testPlants1.toString())));
        }
}