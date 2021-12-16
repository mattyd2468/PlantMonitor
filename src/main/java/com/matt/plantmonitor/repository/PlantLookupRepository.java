package com.matt.plantmonitor.repository;

import com.matt.plantmonitor.models.PlantLookup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PlantLookupRepository extends CrudRepository<PlantLookup, Integer> {
    @Query(value = "SELECT ID FROM plant_lookup pl WHERE pl.name = :name",
            nativeQuery = true)
    int getLookupIdByName(
            @Param("name") String name);
}