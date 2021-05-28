package com.cardif.controllers;

import com.cardif.entities.Cargo;
import com.cardif.services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cargo")
public class CargoController {

    @Autowired
    private CargoService service;

    @GetMapping(path = "/find/{id}")
    public ResponseEntity<Cargo> findById(@PathVariable long id) {
        Cargo cargo = service.findById(id);
        if (cargo.getCargoId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(cargo);
    }

    @GetMapping
    public ResponseEntity<List<Cargo>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Cargo> save(@RequestBody Cargo cargo) {
        return new ResponseEntity<>(service.save(cargo), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody Cargo cargo) {
        service.replace(cargo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
