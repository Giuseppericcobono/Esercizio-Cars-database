package com.example.esercizio_operazioni_crud.controller;

import com.example.esercizio_operazioni_crud.entities.Car;
import com.example.esercizio_operazioni_crud.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @PostMapping("/create")
    public Car create(@RequestBody Car car) {
        Car carSaved = carRepository.saveAndFlush(car);
        return carSaved;
    }

    @GetMapping("/read")
    public List<Car> getCars() {
        List carList = carRepository.findAll();
        return carList;
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Car> getSingleCar(@PathVariable Integer id) {
        if(carRepository.existsById(id)){
            return ResponseEntity.ok(carRepository.getReferenceById(id));
        } else {
             return ResponseEntity.ok(new Car());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Car> updateCarType(@PathVariable Integer id, @RequestParam String modelName, @RequestParam String type) {
        if (carRepository.existsById(id)) {
            Car car = carRepository.findById(id).orElse(null);
            if (car != null) {
                car.setModelName(modelName);
                car.setType(type);
                return ResponseEntity.ok(carRepository.save(car));
            }
        }
        return ResponseEntity.ok(new Car());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable Integer id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<Void> deleteAllCars() {
        carRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}