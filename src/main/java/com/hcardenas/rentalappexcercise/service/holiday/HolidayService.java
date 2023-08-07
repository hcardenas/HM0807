package com.hcardenas.rentalappexcercise.service.holiday;

import com.hcardenas.rentalappexcercise.models.requests.CheckoutRequest;

import java.time.LocalDate;

public interface HolidayService {

    Integer calculateBuissDates(CheckoutRequest request);
}
