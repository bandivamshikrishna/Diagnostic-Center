package com.dc.controller;

import com.dc.dto.*;
import com.dc.entity.UserAuthEntity;
import com.dc.service.VendorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/admin/vendor")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService){
        this.vendorService = vendorService;
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createVendor(@Valid @RequestPart("data") VendorCreateRequestDTO vendorCreateRequestDTO , @RequestPart("logo")MultipartFile logo, @AuthenticationPrincipal UserAuthEntity userAuthEntity) throws IOException, IOException {
        return new ResponseEntity<>(vendorService.createVendor(vendorCreateRequestDTO,logo,userAuthEntity), HttpStatus.CREATED);
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
