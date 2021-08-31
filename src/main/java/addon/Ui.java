package addon;

import java.time.LocalDate;

/**
 * Handles some user interface of this interface
 */
public class Ui {

    /**
     * Presents date and time in a specific manner.
     *
     * @param raw LocalDateTime object to be represented.
     * @return String representation of LocalDateTime object, as specified by question.
     */
    public static String printDate(LocalDate raw) {
        String month = raw.getMonth().toString().substring(0, 3);

        return (month + " " + raw.getDayOfMonth() + " " + raw.getYear());
    }
}
