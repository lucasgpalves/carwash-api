package com.mycompany.lavajato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.lavajato.model.Car;
import com.mycompany.lavajato.request.CarRequest;
import com.mycompany.lavajato.service.CarService;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    
    @Autowired
    private CarService carService;

    public ResponseEntity<Car> createCar(@RequestBody CarRequest carRequest){
        Car savedCar = carService.createCar(carRequest);
        return ResponseEntity.ok(savedCar);
    }

}
