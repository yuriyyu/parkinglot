package com.test.parking.core.models.calendars;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yuriy Yugay on 9/13/2017.
 *
 * @author Yuriy Yugay
 */
public final class HolidayCalendar {
    private static final Map<Integer, List<Integer>> yearHolidays = new HashMap<>();
    static {
        List<Integer> julyDays = new ArrayList<>();
        julyDays.add(4); // Independence Day
        yearHolidays.put(7, julyDays);

        List<Integer> decemberDays = new ArrayList<>();
        decemberDays.add(25); // christmas day
        yearHolidays.put(12, julyDays);
    }

    private HolidayCalendar() {

    }

    public static boolean isHoliday(LocalDateTime dateTime) {
        Month month = dateTime.getMonth();
        int monthValue = month.getValue();
        int monthDay = dateTime.getDayOfMonth();
        List<Integer> daysList = yearHolidays.get(monthValue);
        if(daysList != null) {
            return daysList.stream()
                    .filter(day -> day == monthDay)
                    .findFirst()
                    .isPresent();
        }
        return false;
    }
}
