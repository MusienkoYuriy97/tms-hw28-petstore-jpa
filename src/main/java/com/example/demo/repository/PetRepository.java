package com.example.demo.repository;

import com.example.demo.model.Pet;
import com.example.demo.model.PetStatus;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> getByStatus(String status);
    Optional<Pet> getById(int id);
    boolean existsById(long id);
}
