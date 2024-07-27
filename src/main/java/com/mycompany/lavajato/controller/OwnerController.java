package com.mycompany.lavajato.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.lavajato.model.Owner;
import com.mycompany.lavajato.request.OwnerRequest;
import com.mycompany.lavajato.response.OwnerResponse;
import com.mycompany.lavajato.service.OwnerService;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {
    
    @Autowired
    private OwnerService ownerService;

    @PostMapping
    public ResponseEntity<Owner> createOwner(@RequestBody OwnerRequest ownerRequest){
        Owner owner = ownerService.createOwner(ownerRequest);
        return ResponseEntity.ok(owner);
    }

    @GetMapping
    public ResponseEntity<List<OwnerResponse>> getAllOwners() {
        List<OwnerResponse> owners = ownerService.getAllOwners();
        return ResponseEntity.ok(owners);
    }
}
