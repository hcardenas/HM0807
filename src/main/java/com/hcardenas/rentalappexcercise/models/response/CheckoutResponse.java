package com.hcardenas.rentalappexcercise.models.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutResponse {

    private String toolCode;
    private String toolType;
    private String toolBrand;
    private Integer rentalDays;

    @JsonFormat(pattern = "MM/dd/yy")
    private LocalDate checkoutDay;

    @JsonFormat(pattern = "MM/dd/yy")
    private LocalDate dueDate;
    private Long dailyRentalCharge;
    private Integer chargeDays;
    private Double preDiscountCharge;
    private String discountPercent;
    private Double discountAmount;
    private Double finalCharge;

}
