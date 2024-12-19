package com.ecom.controller;

import com.ecom.entity.Address;
import com.ecom.entity.AppUser;
import com.ecom.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/address")
public class AddressController {
    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAddress(@RequestBody Address address,
                                              @AuthenticationPrincipal AppUser user){
         Address savedAddress=addressService.addAddress(address,user);
         if(savedAddress!=null){
             return  new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
         }
         else
             return new ResponseEntity<>("address not added",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String>deleteAddress(@RequestParam String addId){
        addressService.deleteAddress(addId);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }
}
