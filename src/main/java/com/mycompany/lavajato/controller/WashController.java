package com.mycompany.lavajato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.lavajato.model.Wash;
import com.mycompany.lavajato.request.WashRequest;
import com.mycompany.lavajato.service.WashService;

@RestController
@RequestMapping("/api/washes")
public class WashController {

    @Autowired
    private WashService washService;

    @PostMapping
    public ResponseEntity<Wash> createWash(@RequestBody WashRequest washRequest){
        Wash savedWash = washService.createWash(washRequest);
        return ResponseEntity.ok(savedWash);
    }
}
