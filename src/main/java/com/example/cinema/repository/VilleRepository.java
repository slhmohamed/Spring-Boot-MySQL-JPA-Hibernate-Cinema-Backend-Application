package com.example.cinema.repository;

import com.example.cinema.models.Cinema;
import com.example.cinema.models.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VilleRepository extends JpaRepository<Ville,Long> {
}
