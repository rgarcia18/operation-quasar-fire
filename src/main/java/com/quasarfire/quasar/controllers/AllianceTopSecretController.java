package com.quasarfire.quasar.controllers;

import com.quasarfire.quasar.entities.TopSecretIn;
import com.quasarfire.quasar.exceptions.MessageException;
import com.quasarfire.quasar.services.AllianceTopSecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AllianceTopSecretController {

    @Autowired
    AllianceTopSecretService allianceTopSecretService;

    @RequestMapping(path = "/topsecret")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity allianceTopSecret(@RequestBody TopSecretIn topSecretIn){
        try{
            return ResponseEntity.ok().body(allianceTopSecretService.inputData(topSecretIn));
        }catch (MessageException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
