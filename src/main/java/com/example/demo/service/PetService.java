package com.example.demo.service;

import com.example.demo.model.Pet;
import com.example.demo.model.PetStatus;
import com.example.demo.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PetService {
    @Autowired
    private PetRepository petRepository;

    public void save(Pet pet) {
        if (!petRepository.existsById(pet.getId())){
            petRepository.save(pet);
        }
    }

    public void update(Pet pet) {
        if (petRepository.existsById(pet.getId())){
            petRepository.save(pet);
        }
    }

    public List<Pet> listByStatus(String[] status){
        List<Pet> result = Arrays
                .stream(status)
                .flatMap(s -> petRepository.getByStatus(s).stream())
                .collect(Collectors.toList());
        return result;
    }

    public Pet getById(int petId){
        Optional<Pet> byId = petRepository.getById(petId);
        return byId.orElse(null);
    }

    public void update(int petId, String name, String status) {
        if (petRepository.existsById(petId) && PetStatus.isExist(status)){
            Pet pet = petRepository.getById(petId).get();
            pet.setName(name);
            pet.setStatus(PetStatus.valueOf(status.toUpperCase()));

            petRepository.save(pet);
        }
    }

    public void delete(int petId) {
        if (petRepository.existsById(petId)){
            Pet pet = petRepository.getById(petId).get();
            petRepository.delete(pet);
        }
    }
}
