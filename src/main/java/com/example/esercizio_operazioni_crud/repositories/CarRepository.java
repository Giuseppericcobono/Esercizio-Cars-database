package com.example.esercizio_operazioni_crud.repositories;

import com.example.esercizio_operazioni_crud.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
}