
package com.klef.ms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.ms.entity.FuelStation;

@Repository
public interface FuelStationRepository
        extends JpaRepository<FuelStation, Long> {

    List<FuelStation> findByName(String name);

    List<FuelStation> findByType(String type);
}
