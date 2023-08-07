package com.hcardenas.rentalappexcercise.controller;


import com.hcardenas.rentalappexcercise.models.requests.CheckoutRequest;
import com.hcardenas.rentalappexcercise.models.response.CheckoutResponse;
import com.hcardenas.rentalappexcercise.service.RentalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RentalAgreementController {

    private final RentalService rentalService;

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@Valid @RequestBody CheckoutRequest request) {

        log.info("processing request: {}", request);
        CheckoutResponse response = rentalService.checkoutAggrement(request);

        return ResponseEntity.ok().body(response);
    }

}
