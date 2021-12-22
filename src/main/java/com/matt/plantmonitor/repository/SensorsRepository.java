package com.matt.plantmonitor.repository;

import com.matt.plantmonitor.models.Sensors;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SensorsRepository extends CrudRepository<Sensors, Integer> {
    @Transactional
    @Modifying
    @Query(value = "delete from plantmonitorDB.sensors where lookup_id = :id",
            nativeQuery = true)
    void deleteSensorsByPlantId(
            @Param("id") String id);
}
