package com.matt.plantmonitor.repository;

import com.matt.plantmonitor.models.Plants;;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PlantsRepository extends CrudRepository<Plants, Integer> {
    @Query(value = "SELECT ID FROM plants p WHERE p.name = :name",
            nativeQuery = true)
    int getLookupIdByName(
            @Param("name") String name);
}