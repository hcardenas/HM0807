package com.hcardenas.rentalappexcercise.service;

import com.hcardenas.rentalappexcercise.MockitoBaseTest;
import com.hcardenas.rentalappexcercise.models.persistance.Tools;
import com.hcardenas.rentalappexcercise.models.persistance.ToolsAttributes;
import com.hcardenas.rentalappexcercise.models.requests.CheckoutRequest;
import com.hcardenas.rentalappexcercise.models.response.CheckoutResponse;
import com.hcardenas.rentalappexcercise.persistance.repositories.ToolsAttributesRepository;
import com.hcardenas.rentalappexcercise.persistance.repositories.ToolsRepository;
import com.hcardenas.rentalappexcercise.service.holiday.HolidayServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class RentalServiceImplTest extends MockitoBaseTest {

    @Mock
    ToolsRepository toolsRepository;

    @Mock
    ToolsAttributesRepository attributesRepository;

    @Mock
    HolidayServiceImpl holidayService;

    @InjectMocks
    RentalServiceImpl service;


    @Test
    void checkoutAggrement_test2() {
        // setup
        Integer rentalCount = 3;
        CheckoutRequest request = CheckoutRequest.builder()
                .toolCode("LADW")
                .checkoutDate(LocalDate.of(7,2, 20))
                .rentalDayCount(rentalCount)
                .discount(10)
                .build();

        Tools tool = new Tools("LADW", "Ladder", "Werner", null);
        ToolsAttributes attributes = new ToolsAttributes("Ladder", (long) 1.99, true, true, false, null);

        when(toolsRepository.findById(request.getToolCode())).thenReturn(Optional.of(tool));
        when(attributesRepository.findById(tool.getToolType())).thenReturn(Optional.of(attributes));
        when(holidayService.calculateBuissDates(request)).thenReturn(rentalCount);

        // test
        CheckoutResponse response = service.checkoutAggrement(request);

        assertThat(response.getFinalCharge(), equalTo(2.7));


        // verify
        verify(toolsRepository, times(1)).findById(request.getToolCode());
        verify(attributesRepository, times(1)).findById(tool.getToolType());
        verify(holidayService).calculateBuissDates(request);

    }

    @Test
    void checkoutAggrement_test3() {
        // setup
        Integer rentalCount = 5;
        CheckoutRequest request = CheckoutRequest.builder()
                .toolCode("CHNS")
                .checkoutDate(LocalDate.of(7,2, 15))
                .rentalDayCount(rentalCount)
                .discount(25)
                .build();

        Tools tool = new Tools("CHNS", "Chainsaw", "Stihl", null);
        ToolsAttributes attributes = new ToolsAttributes("Chainsaw", (long) 1.49, true, false, true, null);

        when(toolsRepository.findById(request.getToolCode())).thenReturn(Optional.of(tool));
        when(attributesRepository.findById(tool.getToolType())).thenReturn(Optional.of(attributes));
        when(holidayService.calculateBuissDates(request)).thenReturn(rentalCount);

        // test
        CheckoutResponse response = service.checkoutAggrement(request);

        assertThat(response.getFinalCharge(), equalTo(3.75));


        // verify
        verify(toolsRepository, times(1)).findById(request.getToolCode());
        verify(attributesRepository, times(1)).findById(tool.getToolType());
        verify(holidayService).calculateBuissDates(request);

    }

    @Test
    void checkoutAggrement_test4() {
        // setup
        Integer rentalCount = 6;
        CheckoutRequest request = CheckoutRequest.builder()
                .toolCode("JAKD")
                .checkoutDate(LocalDate.of(9,3, 15))
                .rentalDayCount(rentalCount)
                .discount(0)
                .build();

        Tools tool = new Tools("JAKD", "Jackhammer", "DeWalt", null);
        ToolsAttributes attributes = new ToolsAttributes("Jackhammer", (long) 2.99, true, false, false, null);

        when(toolsRepository.findById(request.getToolCode())).thenReturn(Optional.of(tool));
        when(attributesRepository.findById(tool.getToolType())).thenReturn(Optional.of(attributes));
        when(holidayService.calculateBuissDates(request)).thenReturn(rentalCount);

        // test
        CheckoutResponse response = service.checkoutAggrement(request);

        assertThat(response.getFinalCharge(), equalTo(12.0));


        // verify
        verify(toolsRepository, times(1)).findById(request.getToolCode());
        verify(attributesRepository, times(1)).findById(tool.getToolType());
        verify(holidayService).calculateBuissDates(request);

    }

    @Test
    void checkoutAggrement_test5() {
        // setup
        Integer rentalCount = 9;
        CheckoutRequest request = CheckoutRequest.builder()
                .toolCode("JAKR")
                .checkoutDate(LocalDate.of(7,2, 15))
                .rentalDayCount(rentalCount)
                .discount(0)
                .build();

        Tools tool = new Tools("JAKR", "Jackhammer", "Ridgid", null);
        ToolsAttributes attributes = new ToolsAttributes("Jackhammer", (long) 2.99, true, false, false, null);

        when(toolsRepository.findById(request.getToolCode())).thenReturn(Optional.of(tool));
        when(attributesRepository.findById(tool.getToolType())).thenReturn(Optional.of(attributes));
        when(holidayService.calculateBuissDates(request)).thenReturn(rentalCount);

        // test
        CheckoutResponse response = service.checkoutAggrement(request);

        assertThat(response.getFinalCharge(), equalTo(18.0));


        // verify
        verify(toolsRepository, times(1)).findById(request.getToolCode());
        verify(attributesRepository, times(1)).findById(tool.getToolType());
        verify(holidayService).calculateBuissDates(request);

    }

    @Test
    void checkoutAggrement_test6() {
        // setup
        Integer rentalCount = 4;
        CheckoutRequest request = CheckoutRequest.builder()
                .toolCode("JAKR")
                .checkoutDate(LocalDate.of(7,2, 20))
                .rentalDayCount(rentalCount)
                .discount(50)
                .build();

        Tools tool = new Tools("JAKR", "Jackhammer", "Ridgid", null);
        ToolsAttributes attributes = new ToolsAttributes("Jackhammer", (long) 2.99, true, false, false, null);

        when(toolsRepository.findById(request.getToolCode())).thenReturn(Optional.of(tool));
        when(attributesRepository.findById(tool.getToolType())).thenReturn(Optional.of(attributes));
        when(holidayService.calculateBuissDates(request)).thenReturn(rentalCount);

        // test
        CheckoutResponse response = service.checkoutAggrement(request);

        assertThat(response.getFinalCharge(), equalTo(4.0));


        // verify
        verify(toolsRepository, times(1)).findById(request.getToolCode());
        verify(attributesRepository, times(1)).findById(tool.getToolType());
        verify(holidayService).calculateBuissDates(request);

    }



}