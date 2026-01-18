package com.dc.controller;


import com.dc.dto.MedicalTestCreateRequestDTO;
import com.dc.dto.MedicalTestResponseDTO;
import com.dc.dto.MedicalTestUpdateRequestDTO;
import com.dc.service.MedicalTestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicaltest")
public class MedicalTestController {

    private final MedicalTestService medicalTestService;

    public MedicalTestController(MedicalTestService medicalTestService){
        this.medicalTestService = medicalTestService;
    }

    @PostMapping
    public ResponseEntity<String> createMedicalTest(@Valid @RequestBody MedicalTestCreateRequestDTO medicalTestCreateRequestDTO){
        return new ResponseEntity<>(medicalTestService.createMedicalTest(medicalTestCreateRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalTestResponseDTO> getMedicalTestByID(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(medicalTestService.getMedicalTestByID(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateMedicalTestByID(@PathVariable(name = "id") Long id, @Valid @RequestBody MedicalTestUpdateRequestDTO medicalTestUpdateRequestDTO){
        return new ResponseEntity<>(medicalTestService.updateMedicalTestById(id,medicalTestUpdateRequestDTO),HttpStatus.OK);
    }
}
