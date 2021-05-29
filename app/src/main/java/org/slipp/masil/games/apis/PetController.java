package org.slipp.masil.games.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
public class PetController implements PetApi{
    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<Void> addPet(Pet body) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deletePet(Long petId, String apiKey) {
        return null;
    }

    @Override
    public ResponseEntity<List<Pet>> findPetsByStatus(List<String> status) {
        return null;
    }

    @Override
    public ResponseEntity<List<Pet>> findPetsByTags(List<String> tags) {
        return null;
    }

    @Override
    public ResponseEntity<Pet> getPetById(Long petId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updatePet(Pet body) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updatePetWithForm(Long petId, String name, String status) {
        return null;
    }

    @Override
    public ResponseEntity<ModelApiResponse> uploadFile(Long petId, String additionalMetadata, MultipartFile file) {
        return null;
    }
}
