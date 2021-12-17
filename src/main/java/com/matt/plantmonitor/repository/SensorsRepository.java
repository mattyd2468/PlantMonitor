package com.matt.plantmonitor.repository;

import com.matt.plantmonitor.models.Sensors;
import org.springframework.data.repository.CrudRepository;

public interface SensorsRepository extends CrudRepository<Sensors, Integer> {
}
