package com.cardif.controller;

import com.cardif.entity.Position;
import com.cardif.service.PositionService;
import com.cardif.util.Validation;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "REST API - Position")
@CrossOrigin(origins = "*")
public class PositionController {

    @Autowired
    private PositionService service;

    @GetMapping("/position/find/{id}")
    public ResponseEntity<Position> findById(@PathVariable int id) {
        Position position = service.findById(id);

        if (Validation.isZero(position.getPositionId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(position);
    }

    @GetMapping("/positions")
    public ResponseEntity<List<Position>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/position")
    public ResponseEntity<Position> save(@RequestBody Position body) {
        return new ResponseEntity<>(service.save(body), HttpStatus.CREATED);
    }

    @PutMapping("/position")
    public ResponseEntity<Void> replace(@RequestBody Position body) {
        service.replace(body);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/position/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
