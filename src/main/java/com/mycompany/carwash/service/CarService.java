package com.mycompany.carwash.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.carwash.model.Car;
import com.mycompany.carwash.model.Owner;
import com.mycompany.carwash.repository.CarRepository;
import com.mycompany.carwash.repository.OwnerRepository;
import com.mycompany.carwash.request.CarRequest;
import com.mycompany.carwash.response.CarResponse;

@Service
public class CarService {
    
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    public CarResponse createCar(CarRequest carRequest){
        Car car = new Car();

        car.setLicensePlate(carRequest.licensePlate());
        car.setModel(carRequest.model());
        car.setColor(carRequest.color());

        if(carRequest.ownerId() != null) {
            Optional<Owner> ownerOptional = ownerRepository.findById(carRequest.ownerId());
            ownerOptional.ifPresent(car::setOwner);
        }

        Car savedCar = carRepository.save(car);
        UUID ownerId = car.getOwner() != null ? car.getOwner().getId() : null;
        return new CarResponse(savedCar.getId(), savedCar.getLicensePlate(), savedCar.getModel(), savedCar.getColor(), ownerId);
    }

    public List<CarResponse> getAllCars() {
        return carRepository.findAll().stream()
            .map(car -> new CarResponse(
                car.getId(),
                car.getLicensePlate(),
                car.getModel(),
                car.getColor(),
                car.getOwner() != null ? car.getOwner().getId() : null))
            .collect(Collectors.toList());
    }

    public CarResponse getCarById(UUID id) {
        Car car = carRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Car not found"));

        UUID ownerId = car.getOwner() != null ? car.getOwner().getId() : null;

        return new CarResponse(car.getId(), car.getLicensePlate(), car.getModel(), car.getColor(), ownerId);
    }

    public CarResponse updateCar(UUID id, CarRequest carRequest){
        Optional<Car> carOptional = carRepository.findById(id);
        
        if(carOptional.isPresent()) {
            Car car = carOptional.get();

            car.setLicensePlate(carRequest.licensePlate());
            car.setModel(carRequest.model());
            car.setColor(carRequest.color());

            if(carRequest.ownerId() != null) {
                Optional<Owner> ownerOptional = ownerRepository.findById(carRequest.ownerId());
                ownerOptional.ifPresent(car::setOwner);
            } else {
                car.setOwner(null);
            }

            Car updatedCar = carRepository.save(car);
            UUID ownerId = updatedCar.getOwner() != null ? updatedCar.getOwner().getId() : null;

            return new CarResponse(updatedCar.getId(), updatedCar.getLicensePlate(), updatedCar.getModel(), updatedCar.getColor(), ownerId);
        } else {
            throw new RuntimeException("Car not found");
        }
    }

    public void deleteCarById(UUID id){
        if(carRepository.existsById(id)) {
            carRepository.deleteById(id);
        } else {
            throw new RuntimeException("Car not found");
        }

    }

}
