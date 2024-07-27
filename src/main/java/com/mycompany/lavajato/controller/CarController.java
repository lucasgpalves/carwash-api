package com.mycompany.lavajato.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.lavajato.request.CarRequest;
import com.mycompany.lavajato.response.CarResponse;
import com.mycompany.lavajato.service.CarService;

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

    @PutMapping("{id}/status")
    public ResponseEntity<CarResponse> updateCarStatus(@PathVariable UUID id, CarRequest carRequest) {
        CarResponse car = carService.updateCarStatus(id, carRequest);
        return ResponseEntity.ok(car);
    }

}
