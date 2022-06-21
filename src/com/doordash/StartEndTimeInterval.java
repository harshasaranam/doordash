package com.doordash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StartEndTimeInterval {
    class Time {
        int day;
        int hour;
        int min;
        boolean dayTime;

        Time(int day, int hour, int min, boolean isDay) {
            this.day = day;
            this.hour = hour;
            this.min = min;
            this.dayTime = isDay;
        }

        private void add(int mins) {
            hour += (mins + min) / 60;
            min = (mins + min) % 60;
            if (hour == 12) {
                dayTime = !dayTime;
                hour = 0;
                if (dayTime) {
                    day = (day + 1) % 7;
                }
            }
        }

        private int getNumeric() {
            int addHour = hour;
            if (format.equals("24 HOUR")) {
                addHour = dayTime ? hour : hour + 12;
            }

            int dayAndHour = (day * 100) + addHour;
            return (dayAndHour * 100) + min;
        }

        private boolean equals(Time t2) {
            return day == t2.day &&
                    hour == t2.hour &&
                    min == t2.min &&
                    Boolean.compare(dayTime, t2.dayTime) == 0;
        }
    }

    private Map<String, Integer> mapDays = new HashMap<>();
    private String format;

    private List<Integer> getIntervals(String start, String end, String format) {
        this.format = format;
        mapDays();
        List<Integer> intervals = new ArrayList<>();
        Time startTime = getTime(start);
        Time endTime = getTime(end);
        while (!startTime.equals(endTime)) {
            startTime.add(5);
            intervals.add(startTime.getNumeric());
        }
        return intervals;
    }

    private Time getTime(String time) {
        String[] info = time.split(" ");
        String[] hrMin = info[1].split(":");
        boolean dayTime = info[2].equals("am");
        return new Time(mapDays.get(info[0]), Integer.parseInt(hrMin[0]), Integer.parseInt(hrMin[1]), dayTime);
    }

    private void mapDays() {
        mapDays.put("mon", 1);
        mapDays.put("tue", 2);
        mapDays.put("wed", 3);
        mapDays.put("thu", 4);
        mapDays.put("fri", 5);
        mapDays.put("sat", 6);
        mapDays.put("sun", 7);
    }

    public static void main(String[] args) {
        StartEndTimeInterval startEndTimeInterval = new StartEndTimeInterval();
        List<Integer> data = startEndTimeInterval.getIntervals("mon 10:00 pm", "tue 11:00 pm", "12 HOUR");
        System.out.println(data.size());
        System.out.println(data);

        List<Integer> data2 = startEndTimeInterval.getIntervals("mon 10:00 pm", "tue 11:00 pm", "24 HOUR");
        System.out.println(data2.size());
        System.out.println(data2);

        List<Integer> data3 = startEndTimeInterval.getIntervals("sun 10:00 pm", "mon 11:00 pm", "24 HOUR");
        System.out.println(data3.size());
        System.out.println(data3);
    }
}