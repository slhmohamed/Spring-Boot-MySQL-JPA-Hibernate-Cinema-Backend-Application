package com.example.cinema.repository;

import com.example.cinema.models.Cinema;
import com.example.cinema.models.Seance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeanceRepository extends JpaRepository<Seance,Long> {
}
