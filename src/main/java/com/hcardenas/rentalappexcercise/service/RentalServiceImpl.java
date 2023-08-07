package com.hcardenas.rentalappexcercise.service;

import com.hcardenas.rentalappexcercise.models.persistance.Tools;
import com.hcardenas.rentalappexcercise.models.persistance.ToolsAttributes;
import com.hcardenas.rentalappexcercise.models.requests.CheckoutRequest;
import com.hcardenas.rentalappexcercise.models.response.CheckoutResponse;
import com.hcardenas.rentalappexcercise.persistance.repositories.ToolsAttributesRepository;
import com.hcardenas.rentalappexcercise.persistance.repositories.ToolsRepository;
import com.hcardenas.rentalappexcercise.service.holiday.HolidayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final ToolsRepository toolsRepository;

    private final ToolsAttributesRepository attributesRepository;

    private final HolidayService holidayService;

    @Override
    public CheckoutResponse checkoutAggrement(CheckoutRequest request) {

        Tools tool = toolsRepository.findById(request.getToolCode()).orElseThrow();
        ToolsAttributes attributes = attributesRepository.findById(tool.getToolType()).orElseThrow();
        Integer countDays = holidayService.calculateBuissDates(request);
        Double preDiscount = returnWithPrecision(countDays.doubleValue()) * attributes.getDailyCharge();
        Double discountAmo = returnWithPrecision((request.getDiscount().doubleValue() / 100) * preDiscount);

        return CheckoutResponse.builder()
                .toolCode(request.getToolCode())
                .toolType(tool.getToolType())
                .toolBrand(tool.getBrand())
                .rentalDays(request.getRentalDayCount())
                .checkoutDay(request.getCheckoutDate())
                .dueDate(request.getCheckoutDate().plusDays(request.getRentalDayCount()))
                .dailyRentalCharge(attributes.getDailyCharge())
                .chargeDays(countDays)
                .preDiscountCharge(preDiscount)
                .discountPercent(request.getDiscount() + "%")
                .discountAmount(discountAmo)
                .finalCharge(preDiscount - discountAmo)
                .build();

    }

    private Double returnWithPrecision(Double number) {
        return new BigDecimal(number).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
