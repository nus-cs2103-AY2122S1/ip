package addon;

import java.time.LocalDateTime;

public class Ui {

    public static String bar = " -------------------------------------------------------------";

    public static String printDate(LocalDateTime raw, boolean time) {
        String month = raw.getMonth().toString().substring(0, 3);
        int hour = raw.getHour();
        int mins = raw.getMinute();
        return month + " " + raw.getDayOfMonth() + " " + raw.getYear() + (time ? ( " @ " + (hour < 10 ? "0" : "") + hour + (mins < 10 ? "0" : "") + raw.getMinute()) : "");
    }

    /**
     * Exception that handles improperly formatted command.
     */
    public static class IncorrectFormatException extends Exception {
        public IncorrectFormatException(String errorMessage) {
            super("\n" + bar + "\n    " + errorMessage + "\n    Type \"help\" for help\n" + bar);
        }
    }
}