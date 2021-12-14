package com.matt.plantmonitor.repository;

import com.matt.plantmonitor.models.PlantLookup;
import org.springframework.data.repository.CrudRepository;

public interface PlantLookupRepository extends CrudRepository<PlantLookup, Integer> {

}