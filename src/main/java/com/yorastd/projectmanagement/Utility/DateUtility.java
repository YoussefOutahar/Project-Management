package com.yorastd.projectmanagement.Utility;

import java.util.Date;
import java.util.List;

public class DateUtility {
    public static Date findMostRecentDate(List<Date> dateList) {
        if (dateList == null || dateList.isEmpty()) {
            return null; // Handle empty list
        }

        Date mostRecentDate = dateList.get(0); // Initialize with the first date

        for (Date date : dateList) {
            if (date.after(mostRecentDate)) {
                mostRecentDate = date; // Update the most recent date
            }
        }

        return mostRecentDate;
    }
}
