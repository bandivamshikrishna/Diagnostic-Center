package com.dc.service;

import com.dc.dto.VendorCreateRequestDTO;
import com.dc.dto.VendorResponseDTO;
import com.dc.dto.VendorUpdateRequestDTO;

import java.io.IOException;

public interface VendorService {
    public String createVendor(VendorCreateRequestDTO vendorCreateRequestDTO) throws IOException;
    public void getAllActiveVendors();
    public VendorResponseDTO getVendorById(long id);
    public String updateVendorById(long id, VendorUpdateRequestDTO vendorUpdateRequestDTO);
    public Long getVendorMaxNoOfUsers(Long id);
}