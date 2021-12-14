package com.matt.plantmonitor.repository;

import com.matt.plantmonitor.models.Plants;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlantsRepository extends CrudRepository<Plants, Integer> {
}