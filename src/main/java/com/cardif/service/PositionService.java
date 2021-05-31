package com.cardif.service;

import com.cardif.entity.Position;
import com.cardif.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionRepository repository;

    public Position findById(int id) {
        return repository.findById(id).orElse(new Position());
    }

    public List<Position> findAll() {
        return repository.findAll();
    }

    public Position save(Position position) {
        return repository.save(position);
    }

    public void replace(Position position) {
        if (findById(position.getPositionId()).getPositionId() > 0) {
            repository.save(position);
        }
    }

    public void delete(int id) {
        repository.delete(findById(id));
    }

}
