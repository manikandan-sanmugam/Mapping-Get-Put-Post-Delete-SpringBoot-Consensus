package com.example.Mapping.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Mapping.model.Mapping;
import com.example.Mapping.repository.MappingRepository;

@RestController
@RequestMapping("/api")
public class MappingController {
    @Autowired
    MappingRepository mappingRepository;

    @GetMapping("/show_all")
    public List<Mapping> getAllValues() {
        return (List<Mapping>) mappingRepository.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Mapping> createValues(@RequestBody Mapping mapping) {
        Mapping _mapping = mappingRepository
                .save(new Mapping ( null, mapping.getFirstname(), mapping.getLastname(), mapping.getPassword()));
        return new ResponseEntity<Mapping>(_mapping, HttpStatus.OK);

    }
     @DeleteMapping("/delete_all")
    public List<Mapping> deleteAllmapping() {
        mappingRepository.deleteAll();
        return (List<Mapping>) mappingRepository.findAll();
    }

    @PutMapping("/insert/{id}")
    public ResponseEntity<Mapping> updatemapping(@PathVariable("id") Long id, @RequestBody Mapping mapping) {
        Optional<Mapping> __mapping = mappingRepository.findById(id);
        if (__mapping.isPresent()) {
            Mapping _mapping = __mapping.get();
            // _mapping.setId(mapping.getId());x
            _mapping.setFirstname(mapping.getFirstname());
            _mapping.setLastname(mapping.getLastname());
            _mapping.setPassword(mapping.getPassword());
            return new ResponseEntity<Mapping>(mappingRepository.save(_mapping), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
