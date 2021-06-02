package org.slipp.masil.games.apis;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PetController implements PetsApi {

    @Override
    public ResponseEntity<Void> createPets() {
        return null;
    }

    @Override
    public ResponseEntity<List<Pet>> listPets(Integer limit) {
        return null;
    }

    @Override
    public ResponseEntity<List<Pet>> showPetById(String petId) {
        return null;
    }
}
