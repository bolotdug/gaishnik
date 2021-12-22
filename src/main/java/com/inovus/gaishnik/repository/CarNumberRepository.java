package com.inovus.gaishnik.repository;

import com.inovus.gaishnik.model.CarNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarNumberRepository extends CrudRepository<CarNumber, String> {
}
