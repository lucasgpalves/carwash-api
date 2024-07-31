package com.mycompany.lavajato.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.lavajato.model.Car;
import com.mycompany.lavajato.model.CarStatus;
import com.mycompany.lavajato.model.Owner;
import com.mycompany.lavajato.repository.CarRepository;
import com.mycompany.lavajato.repository.OwnerRepository;
import com.mycompany.lavajato.request.CarRequest;
import com.mycompany.lavajato.request.UpdateCarStatusRequest;
import com.mycompany.lavajato.response.CarResponse;

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
        car.setStatus(carRequest.status());

        if(carRequest.ownerId() != null) {
            Optional<Owner> ownerOptional = ownerRepository.findById(carRequest.ownerId());
            ownerOptional.ifPresent(car::setOwner);
        }

        Car savedCar = carRepository.save(car);
        UUID ownerId = car.getOwner() != null ? car.getOwner().getId() : null;
        return new CarResponse(savedCar.getId(), savedCar.getLicensePlate(), savedCar.getModel(), savedCar.getColor(), savedCar.getStatus(), ownerId);
    }

    public List<CarResponse> getAllCars() {
        return carRepository.findAll().stream()
            .map(car -> new CarResponse(
                car.getId(),
                car.getLicensePlate(),
                car.getModel(),
                car.getColor(),
                car.getStatus(),
                car.getOwner() != null ? car.getOwner().getId() : null))
            .collect(Collectors.toList());
    }

    public CarResponse getCarById(UUID id) {
        Car car = carRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Car not found"));

        UUID ownerId = car.getOwner() != null ? car.getOwner().getId() : null;

        return new CarResponse(car.getId(), car.getLicensePlate(), car.getModel(), car.getColor(), car.getStatus(), ownerId);
    }

    public List<CarResponse> getCarsByStatus(CarStatus status) {
        return carRepository.findByStatus(status).stream()
                .map(car -> new CarResponse(car.getId(), 
                    car.getLicensePlate(), 
                    car.getModel(), 
                    car.getColor(), 
                    car.getStatus(), 
                    car.getOwner() != null ? car.getOwner().getId() : null))
                .collect(Collectors.toList());
    }

    public CarResponse updateCar(UUID id, CarRequest carRequest){
        Optional<Car> carOptional = carRepository.findById(id);
        
        if(carOptional.isPresent()) {
            Car car = carOptional.get();

            car.setLicensePlate(carRequest.licensePlate());
            car.setModel(carRequest.model());
            car.setColor(carRequest.color());
            car.setStatus(carRequest.status());

            if(carRequest.ownerId() != null) {
                Optional<Owner> ownerOptional = ownerRepository.findById(carRequest.ownerId());
                ownerOptional.ifPresent(car::setOwner);
            } else {
                car.setOwner(null);
            }

            Car updatedCar = carRepository.save(car);
            UUID ownerId = updatedCar.getOwner() != null ? updatedCar.getOwner().getId() : null;

            return new CarResponse(updatedCar.getId(), updatedCar.getLicensePlate(), updatedCar.getModel(), updatedCar.getColor(), updatedCar.getStatus(), ownerId);
        } else {
            throw new RuntimeException("Car not found");
        }
    }

    public CarResponse updateCarStatus(UUID id, UpdateCarStatusRequest updateCarStatusRequest) {
        Optional<Car> carOptional = carRepository.findById(id);

        if(carOptional.isPresent()) {
            Car car = carOptional.get();

            car.setStatus(updateCarStatusRequest.status());

            Car updatedCar = carRepository.save(car);
            UUID ownerId = car.getOwner() != null ? car.getOwner().getId() : null;

            return new CarResponse(updatedCar.getId(), updatedCar.getLicensePlate(), updatedCar.getModel(), updatedCar.getColor(), updatedCar.getStatus(), ownerId);
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
