package com.in.GymManagementSystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in.GymManagementSystem.dto.TrainerDTO;
import com.in.GymManagementSystem.services.TrainerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/trainers")
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173", "http://localhost:5174", "http://127.0.0.1:5174"})
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    @GetMapping
    public ResponseEntity<List<TrainerDTO>> getAllTrainers() {
        return ResponseEntity.ok(trainerService.getAllTrainers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerDTO> getTrainerById(@PathVariable Long id) {
        return ResponseEntity.ok(trainerService.getTrainerById(id));
    }

    @PostMapping
    public ResponseEntity<TrainerDTO> createTrainer(@Valid @RequestBody TrainerDTO trainerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trainerService.createTrainer(trainerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainerDTO> updateTrainer(@PathVariable Long id, @Valid @RequestBody TrainerDTO trainerDTO) {
        return ResponseEntity.ok(trainerService.updateTrainer(id, trainerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
        return ResponseEntity.noContent().build();
    }
}
