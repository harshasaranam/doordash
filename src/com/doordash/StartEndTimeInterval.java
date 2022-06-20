package com.doordash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StartEndTimeInterval {
    static class Time {
        int day = -1;
        int hour = 0;
        int min = 0;
        boolean am = false;

        public Time(int day, int hour, int min, boolean am) {
            this.day = day;
            this.hour = hour;
            this.min = min;
            this.am = am;
        }

        void add(int mins) {
            hour += (mins + min) / 60;
            min = (mins + min) % 60;
            if (hour >= 13) {
                am = !am;
                hour = hour % 12;
                if (am)
                    day += 1;
            }
        }

        int getNumeric() {
            return (((day * 100) + hour) * 100) + min;
        }

        boolean equals(Time t2) {
            return day == t2.day &&
                    hour == t2.hour &&
                    min == t2.min &&
                    Boolean.compare(am, t2.am) == 0;
        }
    }

    static Map<String, Integer> mapDays = new HashMap<>();

    static List<Integer> getIntervals(String start, String end) {
        mapDays();
        List<Integer> intervals = new ArrayList<>();
        Time startTime = getTime(start);
        Time endTime = getTime(end);
        while (!startTime.equals(endTime)) {
            startTime.add(5);
       //     System.out.println(startTime.getNumeric());
            intervals.add(startTime.getNumeric());
        }
        return intervals;
    }

    static Time getTime(String time) {
        String[] info = time.split(" ");
        String[] hrMin = info[1].split(":");
        boolean am = info[2].equals("am");
        return new Time(mapDays.get(info[0]), Integer.parseInt(hrMin[0]), Integer.parseInt(hrMin[1]), am);
    }

    static void mapDays() {
        mapDays.put("mon", 1);
        mapDays.put("tue", 2);
        mapDays.put("wed", 3);
        mapDays.put("thu", 4);
        mapDays.put("fri", 5);
        mapDays.put("sat", 6);
        mapDays.put("sun", 7);
    }

    public static void main(String[] args) {
        for (Integer interval : getIntervals("mon 10:00 pm", "mon 11:00 pm")) {
            System.out.println("->" + interval);
        }
    }
}