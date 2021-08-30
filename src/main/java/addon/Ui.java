package addon;

import java.time.LocalDateTime;

/**
 * Handles some user interface of this interface
 */
public class Ui {

    public static final String BAR = " -------------------------------------------------------------";

    /**
     * Presents date and time in a specific manner.
     *
     * @param raw LocalDateTime object to be represented.
     * @param time boolean to indicate if the time should be indicated, true if yes.
     * @return String representation of LocalDateTime object, as specified by question.
     */
    public static String printDate(LocalDateTime raw, boolean time) {
        String month = raw.getMonth().toString().substring(0, 3);
        int hour = raw.getHour();
        int mins = raw.getMinute();
        return month + " " + raw.getDayOfMonth() + " " + raw.getYear() + (time ? (" @ " + (hour < 10 ? "0" : "")
                + hour + (mins < 10 ? "0" : "") + raw.getMinute()) : "");
    }

    /**
     * Exception that handles improperly formatted command.
     */
    public static class IncorrectFormatException extends Exception {
        public IncorrectFormatException(String errorMessage) {
            super("\n" + BAR + "\n    " + errorMessage + "\n    Type \"help\" for help\n" + BAR);
        }
    }
}
