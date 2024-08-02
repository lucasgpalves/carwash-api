package com.mycompany.carwash.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.carwash.request.CarRequest;
import com.mycompany.carwash.response.CarResponse;
import com.mycompany.carwash.service.CarService;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    
    @Autowired
    private CarService carService;

    @PostMapping
    public ResponseEntity<CarResponse> createCar(@RequestBody CarRequest carRequest){
        CarResponse savedCar = carService.createCar(carRequest);
        return ResponseEntity.ok(savedCar);
    }

    @GetMapping
    public ResponseEntity<List<CarResponse>> getAllCars(){
        List<CarResponse> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable UUID id){
        CarResponse car = carService.getCarById(id);
        return ResponseEntity.ok(car);
    }

    @PutMapping("{id}")
    public ResponseEntity<CarResponse> updateCar(@PathVariable UUID id, @RequestBody CarRequest carRequest) {
        CarResponse updatedCar = carService.updateCar(id, carRequest);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable UUID id) {
        carService.deleteCarById(id);
        return ResponseEntity.noContent().build();
    }

}
