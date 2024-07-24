package com.mycompany.lavajato.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.lavajato.model.Car;
import com.mycompany.lavajato.model.Wash;
import com.mycompany.lavajato.repository.CarRepository;
import com.mycompany.lavajato.repository.WashRepository;
import com.mycompany.lavajato.request.WashRequest;
import com.mycompany.lavajato.response.WashResponse;

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

        Wash savedWash = washRepository.save(wash);
        return new WashResponse(savedWash.getId(), savedWash.getDescription(), savedWash.getAmount(), savedWash.isPaid(), car.getId());
    }

    public List<WashResponse> getAllWashes(){
        return washRepository.findAll().stream()
            .map(wash -> new WashResponse(wash.getId(),
                wash.getDescription(),
                wash.getAmount(), 
                wash.isPaid(), 
                wash.getCar().getId()))
            .collect(Collectors.toList());
    }


}
