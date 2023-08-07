package com.hcardenas.rentalappexcercise.models.requests;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequest {

    @JsonProperty(value = "tool_code")
    private String toolCode;

    @JsonProperty(value = "rental_day_count")
    @NotNull(message = "Rental Days cannot be null")
    @Positive(message = "rental days should be greater than 1")
    private Integer rentalDayCount;

    @Min(value = 0, message = "Discount should be between 0 - 100")
    @Max(value = 100, message = "Discount should be between 0 - 100")
    private Integer discount;

    @JsonProperty(value = "checkout_date")
    @JsonFormat(pattern = "MM/dd/yy")
    private LocalDate checkoutDate;
}
