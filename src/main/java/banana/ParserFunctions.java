package banana;

import java.time.LocalDate;

/**
 * The Parser Functions class
 * consists of additional
 * functions to handle the user's
 * commands.
 *
 * @author: Ravi Ananya
 */
public class ParserFunctions {

    /**
     * Gets the list of tasks.
     *
     * @param tasks the list of tasks.
     * @return the tasks in string format.
     */
    public static String getItems(TaskList tasks) {
        String collection = "";
        for (int index = 0; index < tasks.getSize(); index++) {
            if (index != 0) {
                collection += "     ";
            }
            String info = tasks.getTask(index).toString();
            collection += Integer.toString(index + 1) + "." + info;
            if (index != tasks.getSize() - 1) {
                collection += "\n";
            }
        }
        return collection;
    }

    /**
     * Gets the date and time in correct
     * notation/format if necessary.
     *
     * @param info the task information.
     * @param type the Task type.
     * @return the new Task.
     */
    public static Task getDateAndTime(String[] info, String type) {
        String[] potentialDate = info[1].split(" ");
        LocalDate date = null;
        if (!getTime(potentialDate[0]).equals("")) {
            info[1] = getTime(potentialDate[0]);
        } else if (potentialDate.length > 1 &&
                !getTime(potentialDate[1]).equals("")) {
            potentialDate[1] = getTime(potentialDate[1]);
            info[1] = potentialDate[0] + " " + potentialDate[1];
        }
        if (potentialDate.length > 1 && potentialDate[0].contains("/")) {
            date = LocalDate.parse(parseDates(potentialDate[0]));
        }
        if (type.equals("deadline")) {
            if (date != null) {
                return new Deadline(info[0], date, potentialDate[1]);
            } else {
                return new Deadline(info[0], info[1]);
            }
        } else {
            if (date != null) {
                return new Event(info[0], date, potentialDate[1]);
            } else {
                return new Event(info[0], info[1]);
            }
        }
    }

    /**
     * Changes the date format to be
     * parsed by LocalDate.
     *
     * @param date the old-format date.
     * @return the new-format date.
     */
    public static String parseDates(String date) {
        String[] sepIntoYearMonthDate = date.split("/");
        assert sepIntoYearMonthDate.length == 3;
        if (Integer.parseInt(sepIntoYearMonthDate[0]) < 10) {
            sepIntoYearMonthDate[0] = "0" + sepIntoYearMonthDate[0];
        }
        return sepIntoYearMonthDate[2] + "-" +
                sepIntoYearMonthDate[1] + "-" + sepIntoYearMonthDate[0];
    }

    /**
     * Changes the time format.
     *
     * @param time the old-format time.
     * @return the new-format time.
     */
    public static String getTime(String time) {
        try {
            int timeVal = Integer.parseInt(time) / 100;
            if (timeVal > 12) {
                timeVal -= 12;
            }
            return Integer.toString(timeVal) + "pm";
        } catch (NumberFormatException e) {
            return "";
        }
    }

    /**
     * Checks if index is in bounds
     *
     * @param input the user input
     * @param size  the number of list items
     * @return whether index is out of bounds
     */
    public static boolean isOutOfBounds(String input, int size) {
        String[] splitInput = input.split(" ");
        if (splitInput.length == 2) {
            int index = Integer.parseInt(splitInput[1]);
            if (index < 1 || index > size) {
                return true;
            }
        } else if (splitInput.length == 1) {
            return true;
        }
        return false;
    }
}
