package com.example.pet;

import com.example.pet.models.Pet;
import com.example.pet.repositories.PetRepository;
import com.example.pet.services.PetService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class PetTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    @Test
    public void testFindAll() {
        List<Pet> pets = new ArrayList<>();
        pets.add(new Pet(1L ,2, 100.0, "Golden Retriever", "Available"));
        pets.add(new Pet(1L ,1, 50.0, "Persian", "Sold"));
        Mockito.when(petRepository.findAll()).thenReturn(pets);

        List<Pet> result = petService.findAll();

        Assert.assertEquals(pets, result);
    }

    @Test
    public void testFindById() {
        Pet pet = new Pet(1L, 2, 100.0, "Golden Retriever", "Available");
        Mockito.when(petRepository.findById(1L)).thenReturn(Optional.of(pet));

        Optional<Pet> result = petService.findById(1L);

        Assert.assertEquals(pet, result.get());
    }

    @Test
    public void testSave() {
        Pet pet = new Pet(1L,2, 100.0, "Golden Retriever", "Available");
        Mockito.when(petRepository.save(pet)).thenReturn(pet);

        Pet result = petService.save(pet);

        Assert.assertEquals(pet, result);
    }

    @Test
    public void testUpdate() {
        Pet pet = new Pet(1L, 2, 100.0, "Golden Retriever", "Available");
        Mockito.when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
        Mockito.when(petRepository.save(pet)).thenReturn(pet);

        Pet result = petService.update(1L, pet);

        Assert.assertEquals(pet, result);
    }

    @Test
    public void testDeleteById() {
        petService.deleteById(1L);

        Mockito.verify(petRepository, Mockito.times(1)).deleteById(1L);
    }
}
