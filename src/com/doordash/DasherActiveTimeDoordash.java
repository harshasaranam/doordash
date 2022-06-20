package com.doordash;

import java.util.ArrayList;
import java.util.List;

public class DasherActiveTimeDoordash {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        List<String> activity = new ArrayList<>();
        activity.add("8:30am pickup");
        activity.add("9:10am dropoff");
        activity.add("10:20am pickup");
        activity.add("12:15pm pickup");
        activity.add("12:45pm dropoff");
        activity.add("2:25pm dropoff");

        System.out.println(getActiveTimes(activity));
    }

    private static int getActiveTimes(List<String> activity) {
        int timer = 0;
        int startTime = 0;
        int endTime = 0;
        int result = 0;
        for(String currentActivity : activity) {
            String[] split = currentActivity.split("\\s+");
            String timing = split[0];
            String method = split[1];
            if(method.equals("pickup")) {
                timer++;
                if(timer == 1) {
                    startTime = getMinutes(timing);
                }
            } else {
                timer--;
                if(timer == 0) {
                    endTime = getMinutes(timing);
                }
            }
            if(timer == 0) {
                result += endTime - startTime;
            }
        }
        return result;
    }

    private static int getMinutes(String timing) {
        String split[] = timing.split(":");
        int hours = Integer.parseInt(split[0]);
        int minutes = Integer.parseInt(split[1].substring(0, split[1].length() - 2));
        if(timing.endsWith("am") ||(timing.endsWith("pm") && hours == 12)) {
            return hours * 60 + minutes;
        } else {
            return (hours + 12) * 60 + minutes;
        }
    }
}