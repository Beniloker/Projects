package com.ecm.ecommerce.Service;

import com.ecm.ecommerce.Model.Address;
import com.ecm.ecommerce.Model.DTOs.AddressDTO;

import java.util.List;

public interface AddressService {

        AddressDTO createAddress(AddressDTO addressDTO);

        List<AddressDTO> getAddresses();

        AddressDTO getAddress(Long addressId);

        AddressDTO updateAddress(Long addressId, Address address);

        String deleteAddress(Long addressId);
}
