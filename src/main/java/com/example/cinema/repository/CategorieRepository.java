package com.example.cinema.repository;

import com.example.cinema.models.Categorie;
import com.example.cinema.models.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {
}
