package com.mycompany.lavajato.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.lavajato.request.OwnerRequest;
import com.mycompany.lavajato.response.OwnerResponse;
import com.mycompany.lavajato.service.OwnerService;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {
    
    @Autowired
    private OwnerService ownerService;

    @PostMapping
    public ResponseEntity<OwnerResponse> createOwner(@RequestBody OwnerRequest ownerRequest){
        OwnerResponse owner = ownerService.createOwner(ownerRequest);
        return ResponseEntity.ok(owner);
    }

    @GetMapping
    public ResponseEntity<List<OwnerResponse>> getAllOwners() {
        List<OwnerResponse> owners = ownerService.getAllOwners();
        return ResponseEntity.ok(owners);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponse> getOwnerById(@PathVariable UUID id) {
        OwnerResponse owner = ownerService.getOwnerById(id);
        return ResponseEntity.ok(owner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponse> updateOwnerById(@PathVariable UUID id, @RequestBody OwnerRequest ownerRequest) {
        OwnerResponse owner = ownerService.updateOwnerById(id, ownerRequest);
        return ResponseEntity.ok(owner);
    }
}
