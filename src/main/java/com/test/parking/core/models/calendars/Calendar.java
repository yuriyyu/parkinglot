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
public class Calendar {

    protected Map<Integer, List<Integer>> yearHolidays;

    public Calendar() {
        this.buildHolidaysMap(yearHolidays);
    }

    private void buildHolidaysMap(Map<Integer, List<Integer>> map) {
        if(map == null) {
            this.yearHolidays = new HashMap<>();
        }

        List<Integer> julyDays = new ArrayList<>();
        julyDays.add(4); // Independence Day
        map.put(7, julyDays);

        List<Integer> decemberDays = new ArrayList<>();
        decemberDays.add(25); // christmas day
        map.put(12, julyDays);
    }

    public boolean isHoliday(LocalDateTime dateTime) {
        Month month = dateTime.getMonth();
        int monthValue = month.getValue();
        int monthDay = dateTime.getDayOfMonth();
        List<Integer> daysList = this.yearHolidays.get(monthValue);
        if(daysList != null) {
            return daysList.stream()
                    .filter(day -> day == monthDay)
                    .findFirst()
                    .isPresent();
        }
        return false;
    }
}
