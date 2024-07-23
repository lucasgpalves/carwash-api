package com.mycompany.lavajato.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.lavajato.model.Owner;
import com.mycompany.lavajato.repository.OwnerRepository;
import com.mycompany.lavajato.request.OwnerRequest;

@Service
public class OwnerService {
    
    @Autowired
    private OwnerRepository ownerRepository;

    public Owner createOwner(OwnerRequest ownerRequest){
        Owner owner = new Owner();
        owner.setName(ownerRequest.name());
        owner.setPhoneNumber(ownerRequest.phoneNumber());

        return ownerRepository.save(owner);
    }

}
