package com.cardif.repository;

import com.cardif.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {
}
