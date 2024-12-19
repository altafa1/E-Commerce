package com.ecom.service;

import com.ecom.entity.Address;
import com.ecom.entity.AppUser;

public interface AddressService {
    Address addAddress(Address address, AppUser user);

    public void deleteAddress(String addId);
}
