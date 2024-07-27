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

import com.mycompany.lavajato.request.WashRequest;
import com.mycompany.lavajato.response.WashResponse;
import com.mycompany.lavajato.service.WashService;

@RestController
@RequestMapping("/api/washes")
public class WashController {

    @Autowired
    private WashService washService;

    @PostMapping
    public ResponseEntity<WashResponse> createWash(@RequestBody WashRequest washRequest){
        WashResponse savedWash = washService.createWash(washRequest);
        return ResponseEntity.ok(savedWash);
    }

    @GetMapping
    public ResponseEntity<List<WashResponse>> getAllWashes(){
        List<WashResponse> washes = washService.getAllWashes();
        return ResponseEntity.ok(washes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WashResponse> updateTypeWash(@PathVariable UUID id, @RequestBody WashRequest washRequest) {
        WashResponse wash = washService.updateTypeWash(id, washRequest);
        return ResponseEntity.ok(wash);
    }
}
