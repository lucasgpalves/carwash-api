package com.mycompany.lavajato.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.lavajato.model.Car;
import com.mycompany.lavajato.model.Owner;
import com.mycompany.lavajato.repository.CarRepository;
import com.mycompany.lavajato.repository.OwnerRepository;
import com.mycompany.lavajato.request.CarRequest;

@Service
public class CarService {
    
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    public Car createCar(CarRequest carRequest){
        Car car = new Car();

        car.setLicensePlate(carRequest.licensePlate());
        car.setModel(carRequest.model());
        car.setColor(carRequest.color());
        car.setStatus(carRequest.status());

        if(carRequest.ownerId() != null) {
            Optional<Owner> ownerOptional = ownerRepository.findById(carRequest.ownerId());
            ownerOptional.ifPresent(car::setOwner);
        }

        return carRepository.save(car);


    }

}
