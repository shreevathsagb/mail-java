package com.doorstep.service.Repository;

import com.doorstep.service.Entiity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository<City, Integer> {
}
