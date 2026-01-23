package com.dc.controller;

import com.dc.dto.*;
import com.dc.service.VendorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService){
        this.vendorService = vendorService;
    }


    @PostMapping
    public ResponseEntity<String> createVendor(@Valid @ModelAttribute VendorCreateRequestDTO vendorCreateRequestDTO) throws IOException, IOException {
        return new ResponseEntity<>(vendorService.createVendor(vendorCreateRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorResponseDTO> getVendorByID(@PathVariable(name = "id") long id){
        return new ResponseEntity<>(vendorService.getVendorById(id),HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateVendorByID(@PathVariable(name = "id") long id, @Valid @RequestBody VendorUpdateRequestDTO vendorUpdateRequestDTO){
        return new ResponseEntity<>(vendorService.updateVendorById(id,vendorUpdateRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/create-package")
    public ResponseEntity<String> createVendorPackage(@Valid @RequestBody VendorCreatePackageRequestDTO vendorCreatePackageRequestDTO){
        return new ResponseEntity<>(vendorService.createVendorPackage(vendorCreatePackageRequestDTO),HttpStatus.CREATED);
    }

    @PostMapping("/manage-test")
    public ResponseEntity<String> manageVendorMedicalTests(@Valid @RequestBody VendorManageMedicalTestsDTO vendorManageMedicalTestsDTO){
        return new ResponseEntity<>(vendorService.manageVendorMedicalTests(vendorManageMedicalTestsDTO),HttpStatus.OK);
    }



}
