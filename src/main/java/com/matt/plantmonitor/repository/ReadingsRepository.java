package com.matt.plantmonitor.repository;

import com.matt.plantmonitor.models.Readings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ReadingsRepository extends CrudRepository<Readings, Integer> {
    @Query(value = "select r.ID, r.Sensor_Lookup_ID, r.Plant_Lookup_ID, r.Reading, MAX(r.timestamp) as Timestamp , s.name sensor_Name, s.unit_of_measurement \n" +
            "from plantmonitorDB.readings r\n" +
            "inner join plantmonitorDB.sensors s\n" +
            "on r.sensor_lookup_id = s.id\n" +
            "where r.plant_lookup_id = :id\n" +
            "group by r.Sensor_Lookup_ID;",
            nativeQuery = true)
    Iterable<Readings> getLatestReadingsById(
            @Param("id") String id);

}
