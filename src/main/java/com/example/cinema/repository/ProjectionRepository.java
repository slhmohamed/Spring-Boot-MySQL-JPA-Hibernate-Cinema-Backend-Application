package com.example.cinema.repository;

import com.example.cinema.models.Cinema;
import com.example.cinema.models.Projection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectionRepository  extends JpaRepository<Projection,Long> {
}
