package com.mycompany.carwash.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.mycompany.carwash.events.WashStatusChangeEvent;
import com.mycompany.carwash.model.Car;
import com.mycompany.carwash.model.WashStatus;
import com.mycompany.carwash.model.Wash;
import com.mycompany.carwash.repository.CarRepository;
import com.mycompany.carwash.repository.WashRepository;
import com.mycompany.carwash.request.UpdateWashStatusRequest;
import com.mycompany.carwash.request.WashRequest;
import com.mycompany.carwash.response.WashResponse;

@Service
public class WashService {
    
    @Autowired 
    private WashRepository washRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public WashResponse createWash(WashRequest washRequest) {
        Car car = carRepository.findById(washRequest.carId()).orElseThrow(() -> new RuntimeException("Car not found"));
        Wash wash = new Wash();

        wash.setCar(car);
        wash.setDescription(washRequest.description());
        wash.setAmount(washRequest.amount());
        wash.setPaid(washRequest.isPaid());
        wash.setStatus(WashStatus.PENDING);
        wash.setStartsAt(LocalDateTime.now());
        wash.setEndsAt(null);

        Wash savedWash = washRepository.save(wash);
        return new WashResponse(savedWash.getId(), car.getId(), savedWash.getDescription(), savedWash.getAmount(), savedWash.isPaid(), savedWash.getStatus(), savedWash.getStartsAt(), savedWash.getEndsAt());
    }

    public List<WashResponse> getAllWashes(){
        return washRepository.findAll().stream()
            .map(wash -> new WashResponse(wash.getId(),
                wash.getCar().getId(),
                wash.getDescription(),
                wash.getAmount(), 
                wash.isPaid(), 
                wash.getStatus(),
                wash.getStartsAt(), 
                wash.getEndsAt()))
            .collect(Collectors.toList());
    }

    public WashResponse getWashById(UUID id) {
        Wash wash = washRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Wash not found"));

        UUID carId = wash.getCar().getId();

        return new WashResponse(wash.getId(), carId, wash.getDescription(), wash.getAmount(), wash.isPaid(), wash.getStatus(), wash.getStartsAt(), wash.getEndsAt());
    }

    public List<WashResponse> getWashByStatus(WashStatus status) {
        return washRepository.findByStatus(status).stream()
                .map(wash -> new WashResponse(
                    wash.getId(),
                    wash.getCar().getId(),
                    wash.getDescription(),
                    wash.getAmount(),
                    wash.isPaid(),
                    wash.getStatus(),
                    wash.getStartsAt(),
                    wash.getEndsAt()
                ))
                .collect(Collectors.toList());
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

            return new WashResponse(updatedWash.getId(), carId, updatedWash.getDescription(), updatedWash.getAmount(), updatedWash.isPaid(), wash.getStatus(), wash.getStartsAt(), wash.getEndsAt());
        } else {
            throw new RuntimeException("Wash not found");
        }
    }

    public WashResponse updateWashStatus(UUID id, UpdateWashStatusRequest updateWashStatusRequest) {
        Wash wash = washRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Wash not found"));

        WashStatus currentStatus = wash.getStatus();
        WashStatus newStatus = updateWashStatusRequest.status();

        if (isValidStatusTransaction(currentStatus, newStatus)) {
            //Save car
            wash.setStatus(newStatus);
            Wash updatedWash = washRepository.save(wash);

            eventPublisher.publishEvent(new  WashStatusChangeEvent(this, id, newStatus));

            //Get ownerId
            UUID carId = wash.getCar().getId();
            
            return new WashResponse(updatedWash.getId(), carId, updatedWash.getDescription(), updatedWash.getAmount(), updatedWash.isPaid(), updatedWash.getStatus(), updatedWash.getStartsAt(), updatedWash.getEndsAt());
        } else {
            throw new RuntimeException("Invalid status transition from " + currentStatus + " to " + newStatus);
        }
    }

    public void deleteWashById(UUID id) {
        if(washRepository.existsById(id)) {
            washRepository.deleteById(id);
        } else {
            throw new RuntimeException("Wash not found");
        }
    }

    public void finalizeWash(UUID id) {
        Wash wash = washRepository.findByIdAndEndsAtIsNull(id)
            .orElseThrow(() -> new RuntimeException("Wash not found"));

        validateDoneNotPaidStatus(wash);

        wash.setEndsAt(LocalDateTime.now());
        washRepository.save(wash);
    }

    private void validateDoneNotPaidStatus(Wash wash) {
        // Verifica o status de pagamento
        if (wash.isPaid()) {
            if (wash.getStatus() == WashStatus.DONE) {
                wash.setStatus(WashStatus.READY);
            } else {
                throw new RuntimeException("Wash status must be DONE to transition to READY");
            }
        } else {
            if (wash.getStatus() == WashStatus.DONE) {
                wash.setStatus(WashStatus.DONE_NOT_PAID);
            } else {
                throw new RuntimeException("Wash status must be DONE to transition to DONE_NOT_PAID"); 
            }
        }
        // Salva a lavagem com o novo status
        washRepository.save(wash);
    }
    

    private boolean isValidStatusTransaction(WashStatus currentStatus, WashStatus newStatus) {
        switch (currentStatus) {
            case PENDING:
                return newStatus == WashStatus.WASHING || newStatus == WashStatus.CANCELLED;
            case WASHING:
                return newStatus == WashStatus.DRYING || newStatus == WashStatus.CANCELLED;
            case DRYING:
                return newStatus == WashStatus.DONE || newStatus == WashStatus.CANCELLED;
            case DONE:
                return newStatus == WashStatus.READY || newStatus == WashStatus.DONE_NOT_PAID || newStatus == WashStatus.CANCELLED;
            case DONE_NOT_PAID:
                return newStatus == WashStatus.READY || newStatus == WashStatus.CANCELLED;
            case READY:
                return newStatus == WashStatus.PICKED_UP || newStatus == WashStatus.CANCELLED;
            case PICKED_UP:
                return false;
            case CANCELLED:
                return false;
            default:
                return false;
        }
    }
}
