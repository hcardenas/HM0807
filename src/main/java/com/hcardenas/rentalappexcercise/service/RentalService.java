package com.hcardenas.rentalappexcercise.service;

import com.hcardenas.rentalappexcercise.models.requests.CheckoutRequest;
import com.hcardenas.rentalappexcercise.models.response.CheckoutResponse;

public interface RentalService {
    CheckoutResponse checkoutAggrement(CheckoutRequest request);

}
