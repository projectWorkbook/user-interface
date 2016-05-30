package com.kalis.beata.workmanager.tools;

public class TimeConverter {

    public static String hourToString(int hours) {
        String HH = "";

        if (hours < 10) {
            HH += "0";
        }

        return HH + hours;
    }

    public static String minutesToString(int minutes){
        String mins = "";

        if (minutes < 10) {
            mins += "0";
        }

        return mins + minutes;
    }

    public static String secondsToString(int seconds){
        String secs = "";

        if (seconds < 10) {
            secs += "0";
        }

        return secs + seconds;
    }

    public static String timeInString(long timeInMilis){

        int hours = (int) (timeInMilis / 3600000);
        int minutes = (int) (timeInMilis - hours * 3600000) / 60000;
        int seconds = (int) (timeInMilis - hours * 3600000 - minutes * 60000) / 1000;

        String convertedHours = hourToString(hours);
        String convertedMinutes = minutesToString(minutes);
        String convertedSeconds = secondsToString(seconds);

        return convertedHours + ":" + convertedMinutes + ":" + convertedSeconds;
    }

    public static String timeInMinutesAndSecsonds(long timeInMilis){
        int minutes = (int) timeInMilis / 60000;
        int seconds = (int) (timeInMilis - minutes * 60000) / 1000;

        String convertedMinutes = minutesToString(minutes);
        String convertedSeconds = secondsToString(seconds);

        return convertedMinutes + ":" + convertedSeconds;
    }

    public static long timeInMillis(String time){
        String split[] = time.split(":");
        long hours = Long.parseLong(split[0]);
        long minutes = Long.parseLong(split[1]);
        long seconds = Long.parseLong(split[2]);

        seconds += minutes * 60;
        seconds += hours * 3600;

        return seconds * 1000;
    }
}
