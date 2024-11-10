package com.example.security.security.Mapper;

import com.example.security.security.Dto.VendorDto;
import com.example.security.security.Dto.VendorResponseDto;
import com.example.security.security.Modals.Vendor;

public class VendorMapper {
    public static Vendor mapDtoToVendor(VendorDto dto){
        return new Vendor(
                dto.getId(),
                dto.getUsername(),
                dto.getEmail(),
                dto.getMobile(),
                dto.getAddress(),
                dto.getState(),
                dto.getDistrict(),
                dto.getPincode()
        );
    }

    public static VendorResponseDto mapVendorToDto(Vendor vendor){
        return new VendorResponseDto(
                vendor.getEmail(),
                vendor.getMobile(),
                vendor.getUserid(),
                vendor.getAddress()
        );
    }
}
