package com.matt.plantmonitor.repository;

import com.matt.plantmonitor.models.Readings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ReadingsRepository extends CrudRepository<Readings, Integer> {
    @Query(value = "select r1.* , s.name sensor_Name, s.unit_of_measurement from plantmonitorDB.readings r1 \n" +
            "             inner join plantmonitorDB.sensors s\n" +
            "            on r1.sensor_lookup_id = s.id\n" +
            "            where r1.timestamp in \n" +
            "            (select MAX(r.timestamp) from plantmonitorDB.readings r where r.plant_lookup_id = :id group by r.sensor_lookup_id)",
            nativeQuery = true)
    Iterable<Readings> getLatestReadingsById(
            @Param("id") String id);

}
