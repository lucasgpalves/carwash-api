package com.mycompany.lavajato.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.lavajato.model.Car;
import com.mycompany.lavajato.model.Wash;
import com.mycompany.lavajato.repository.CarRepository;
import com.mycompany.lavajato.repository.WashRepository;
import com.mycompany.lavajato.request.WashRequest;

@Service
public class WashService {
    
    @Autowired 
    private WashRepository washRepository;

    @Autowired
    private CarRepository carRepository;

    public Wash createWash(WashRequest washRequest) {
        Optional<Car> carOptional = carRepository.findById(washRequest.carId());

        if(!carOptional.isPresent()) {
            throw new RuntimeException("Car not found");
        }

        Car car = carOptional.get();
        Wash wash = new Wash();

        wash.setCar(car);
        wash.setDescription(washRequest.description());
        wash.setAmount(washRequest.amount());
        wash.setPaid(washRequest.isPaid());

        return washRepository.save(wash);
    }


}
