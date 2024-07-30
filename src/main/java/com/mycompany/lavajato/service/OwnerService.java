package com.mycompany.lavajato.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.lavajato.model.Owner;
import com.mycompany.lavajato.repository.OwnerRepository;
import com.mycompany.lavajato.request.OwnerRequest;
import com.mycompany.lavajato.response.OwnerResponse;

@Service
public class OwnerService {
    
    @Autowired
    private OwnerRepository ownerRepository;

    public OwnerResponse createOwner(OwnerRequest ownerRequest){
        Owner owner = new Owner();
        owner.setName(ownerRequest.name());
        owner.setPhoneNumber(ownerRequest.phoneNumber());

        Owner savedOwner = ownerRepository.save(owner);
        return new OwnerResponse(savedOwner.getId(), savedOwner.getName(), savedOwner.getPhoneNumber());
    }

    public List<OwnerResponse> getAllOwners() {
        return ownerRepository.findAll().stream()
            .map(owner -> new OwnerResponse(owner.getId(),
                owner.getName(),
                owner.getPhoneNumber()))
            .collect(Collectors.toList());
    }

    public OwnerResponse getOwnerById(UUID id) {
        Optional<Owner> ownerOptional = ownerRepository.findById(id);

        if(ownerOptional.isPresent()) {
            Owner owner = ownerOptional.get();
            return new OwnerResponse(owner.getId(), owner.getName(), owner.getPhoneNumber());
        } else {
            throw new RuntimeException("Owner not found");
        }
    }

    public OwnerResponse updateOwnerById(UUID id, OwnerRequest ownerRequest) {
        Optional<Owner> ownerOptional = ownerRepository.findById(id);

        if(ownerOptional.isPresent()) {
            Owner owner = ownerOptional.get();

            owner.setName(ownerRequest.name());
            owner.setPhoneNumber(ownerRequest.phoneNumber());

            Owner updatedOwner = ownerRepository.save(owner);
            return new OwnerResponse(updatedOwner.getId(), updatedOwner.getName(), updatedOwner.getPhoneNumber());
        } else {
            throw new RuntimeException("Owner not found");
        }
    }

    public void deleteOwnerById(UUID id) {
        if(ownerRepository.existsById(id)) {
            ownerRepository.deleteById(id);
        } else {
            throw new RuntimeException("Owner not found");
        }
    }

}
