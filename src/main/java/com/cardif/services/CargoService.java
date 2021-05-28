package com.cardif.services;

import com.cardif.entities.Cargo;
import com.cardif.repositories.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService {

    @Autowired
    private CargoRepository repository;

    public Cargo findById(long id) {
        return repository.findById(id).orElse(new Cargo());
    }

    public List<Cargo> findAll() {
        return repository.findAll();
    }

    public Cargo save(Cargo cargo) {
        return repository.save(cargo);
    }

    public void replace(Cargo cargo) {
        if (findById(cargo.getCargoId()).getCargoId() != null) {
            repository.save(cargo);
        }
    }

    public void delete(long id) {
        Cargo cargo = findById(id);
        if (cargo.getCargoId() != null) {
            repository.delete(cargo);
        }
    }

}
