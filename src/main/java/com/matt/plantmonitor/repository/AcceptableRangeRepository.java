package com.matt.plantmonitor.repository;

import com.matt.plantmonitor.models.AcceptableRange;
import com.matt.plantmonitor.models.Readings;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AcceptableRangeRepository extends CrudRepository<AcceptableRange, Integer> {
    @Query(value = "select * from plantmonitorDB.acceptable_range where sensor_lookup_id = :id",
            nativeQuery = true)
    AcceptableRange getAcceptableRangeBySensorId(
            @Param("id") String id);

    @Transactional
    @Modifying
    @Query(value = "delete from plantmonitorDB.acceptable_range where plant_lookup_id = :id",
            nativeQuery = true)
    void deleteAcceptableRangeByPlantId(
            @Param("id") String id);

    @Transactional
    @Modifying
    @Query(value = "delete from plantmonitorDB.acceptable_range where sensor_lookup_id = :id",
            nativeQuery = true)
    void deleteAcceptableRangeBySensorId(
            @Param("id") String id);
}
