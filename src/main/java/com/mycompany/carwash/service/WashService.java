package com.mycompany.carwash.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.carwash.model.Car;
import com.mycompany.carwash.model.Wash;
import com.mycompany.carwash.repository.CarRepository;
import com.mycompany.carwash.repository.WashRepository;
import com.mycompany.carwash.request.WashRequest;
import com.mycompany.carwash.response.WashResponse;

@Service
public class WashService {
    
    @Autowired 
    private WashRepository washRepository;

    @Autowired
    private CarRepository carRepository;

    public WashResponse createWash(WashRequest washRequest) {
        Car car = carRepository.findById(washRequest.carId()).orElseThrow(() -> new RuntimeException("Car not found"));
        Wash wash = new Wash();

        wash.setCar(car);
        wash.setDescription(washRequest.description());
        wash.setAmount(washRequest.amount());
        wash.setPaid(washRequest.isPaid());
        wash.setStartsAt(LocalDateTime.now());
        wash.setEndsAt(null);

        Wash savedWash = washRepository.save(wash);
        return new WashResponse(savedWash.getId(), savedWash.getDescription(), savedWash.getAmount(), savedWash.isPaid(), car.getId(), savedWash.getStartsAt(), savedWash.getEndsAt());
    }

    public List<WashResponse> getAllWashes(){
        return washRepository.findAll().stream()
            .map(wash -> new WashResponse(wash.getId(),
                wash.getDescription(),
                wash.getAmount(), 
                wash.isPaid(), 
                wash.getCar().getId(),
                wash.getStartsAt(), 
                wash.getEndsAt()))
            .collect(Collectors.toList());
    }

    public WashResponse getWashById(UUID id) {
        Wash wash = washRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Wash not found"));

        UUID carId = wash.getCar().getId();

        return new WashResponse(wash.getId(), wash.getDescription(), wash.getAmount(), wash.isPaid(), carId, wash.getStartsAt(), wash.getEndsAt());
    }

    public WashResponse updateWashById(UUID id, WashRequest washRequest) {
        Optional<Wash> washOptional = washRepository.findById(id);

        if(washOptional.isPresent()) {
            Wash wash = washOptional.get();
            wash.setDescription(washRequest.description());
            wash.setAmount(washRequest.amount());
            wash.setPaid(washRequest.isPaid());

            Wash updatedWash = washRepository.save(wash);
            UUID carId = wash.getCar() != null ? wash.getCar().getId() : null;

            return new WashResponse(updatedWash.getId(), updatedWash.getDescription(), updatedWash.getAmount(), updatedWash.isPaid(), carId, wash.getStartsAt(), wash.getEndsAt());
        } else {
            throw new RuntimeException("Wash not found");
        }
    }

    public void deleteWashById(UUID id) {
        if(washRepository.existsById(id)) {
            washRepository.deleteById(id);
        } else {
            throw new RuntimeException("Wash not found");
        }
    }
}
