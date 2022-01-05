package com.matt.plantmonitor.services;

import com.matt.plantmonitor.models.AcceptableRange;
import com.matt.plantmonitor.models.Plants;
import com.matt.plantmonitor.models.Readings;
import com.matt.plantmonitor.repository.AcceptableRangeRepository;
import com.matt.plantmonitor.repository.PlantsRepository;
import com.matt.plantmonitor.repository.ReadingsRepository;
import com.matt.plantmonitor.repository.SensorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    @Autowired
    private PlantsRepository plantsRepository;

    @Autowired
    private ReadingsRepository readingsRepository;

    @Autowired
    private AcceptableRangeRepository acceptableRangeRepository;

    public Plants updateStatus(Plants plant) {
        int readingsOutOfBoundsCounter = 0;
        Iterable<Readings> readings = readingsRepository.getLatestReadingsById(plant.getId().toString());
        int countOfSensors = 0;
        for (Readings reading : readings) {
            countOfSensors++;
            AcceptableRange acceptableRange = acceptableRangeRepository.getAcceptableRangeBySensorId(String.valueOf(reading.getSensorLookupId()));
            if (reading.getReading() > acceptableRange.getUpperBoundry() || reading.getReading() < acceptableRange.getLowerBoundry()) {
                readingsOutOfBoundsCounter++;
            }
        }
        if (readingsOutOfBoundsCounter == 0) {
            plant.setStatus("Green");
        } else if (readingsOutOfBoundsCounter < countOfSensors) {
            plant.setStatus("Amber");
        } else {
            plant.setStatus("Red");
        }
        plantsRepository.save(plant);
        return plant;
    }
}
