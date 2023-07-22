package com.example.pet.services;

import com.example.pet.models.Pet;
import com.example.pet.repositories.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PetService {
    private final PetRepository petRepository;

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public Optional<Pet> findById(Long id) {
        return petRepository.findById(id);
    }

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet update(Long id, Pet pet) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isPresent()) {
            Pet existingPet = optionalPet.get();
            existingPet.setAge(pet.getAge());
            existingPet.setPrice(pet.getPrice());
            existingPet.setSpecies(pet.getSpecies());
            existingPet.setStatus(pet.getStatus());
            return petRepository.save(existingPet);
        } else {
            return null;
        }
    }

    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }
}










