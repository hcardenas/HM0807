package com.hcardenas.rentalappexcercise.service.holiday;

import com.hcardenas.rentalappexcercise.models.persistance.Tools;
import com.hcardenas.rentalappexcercise.models.persistance.ToolsAttributes;
import com.hcardenas.rentalappexcercise.models.requests.CheckoutRequest;
import com.hcardenas.rentalappexcercise.persistance.repositories.ToolsAttributesRepository;
import com.hcardenas.rentalappexcercise.persistance.repositories.ToolsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



@Slf4j
@Component
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {

    final static Set<DayOfWeek> WEEKEND = Set.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

    final private ToolsRepository repository;

    final private ToolsAttributesRepository attributesRepository;
    @Override
    public Integer calculateBuissDates(CheckoutRequest request) {
        Tools tool = repository.findById(request.getToolCode()).orElseThrow();
        ToolsAttributes attributes = attributesRepository.findById(tool.getToolType()).orElseThrow();

        LocalDate date = request.getCheckoutDate();
        YearMonth yearMonth = YearMonth.of(date.getYear(), Month.SEPTEMBER);
        LocalDate laborDay = LocalDate.from(getFirstMonday(yearMonth.atDay(1)));

        LocalDate julyFourth = deriveJulyFourthDate(date);
        final Set<LocalDate> holidays = Set.of( julyFourth, laborDay);

        List<LocalDate> days = date.datesUntil(date.plusDays(request.getRentalDayCount())).collect(Collectors.toList());

        Integer daysCount = request.getRentalDayCount();
        for (LocalDate day : days) {
            if (WEEKEND.contains(day.getDayOfWeek()) && !attributes.getWeekendCharge()) {
                daysCount--;
            }
            if ( holidays.contains(day) && !attributes.getHolidayCharge()) {
                daysCount--;
            }
        }

        return daysCount;
    }

    private LocalDate deriveJulyFourthDate(LocalDate date) {
        LocalDate newDate = LocalDate.of(date.getYear(), Month.JULY, 4);
        DayOfWeek dayOfWeek = newDate.getDayOfWeek();

        if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
            newDate = newDate.minusDays(1);
        } else if (dayOfWeek.equals(DayOfWeek.SUNDAY)) {
            newDate = newDate.plusDays(1);
        }

        return newDate;
    }

    private LocalDate getFirstMonday(LocalDate date) {
        LocalDate newDate = date;
        while (newDate.getDayOfWeek() != DayOfWeek.MONDAY) {
            newDate = newDate.plusDays(1);
        }

        return date;
    }

}
