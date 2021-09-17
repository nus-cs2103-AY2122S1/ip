package addon;

import java.time.LocalDate;

/**
 * Handles some user interface of this interface
 */
public class Ui {

    /**
     * Returns a string representation of the date in a specified manner.
     *
     * @param rawLocalDate LocalDate object to be represented.
     * @return String representation of LocalDateTime object, as specified by question.
     */
    public static String printDate(LocalDate rawLocalDate) {
        String month = rawLocalDate.getMonth().toString().substring(0, 3);

        return (rawLocalDate.getDayOfMonth() + " " + month + " " + rawLocalDate.getYear());
    }
}
