package duke.utils;

import duke.exception.DukeException;
import duke.exception.IncorrectFormatException;
import duke.exception.NoNumberException;
import duke.exception.NoTimeException;

import java.util.StringJoiner;

/** Utils class for methods that are useful in more than one class */
public class Utils {

    /**
     *
     * @param str The String to be split
     * @param separator The String for str to be split between
     * @return An array of two Strings, with the first String being words before seperator,
     *         and second string being after the seperator. E.g. splitBetween("a b c", "b") returns
     *         {"a", "c"}
     * @throws DukeException When the seperator cannot be found, or there is nothing after the seperator
     */
    public static String[] splitBetween(String str, String separator) throws DukeException {
        String delimiter = " ";
        String emptyString = "";

        StringJoiner beforeSeparator = new StringJoiner(delimiter);
        StringJoiner afterSeparator = new StringJoiner(delimiter);

        int i = 0;
        boolean isAfterSeparator = false;

        String[] strArray = str.split(delimiter);

        while (i < strArray.length) {
            String currentString = strArray[i];
            if (isAfterSeparator) {
                afterSeparator.add(currentString);
            } else if (currentString.equals(separator)) {
                isAfterSeparator = true;
            } else {
                beforeSeparator.add(currentString);
            }
            i++;
        }

        if (!isAfterSeparator) {
            throw new IncorrectFormatException();
        }

        if (String.valueOf(afterSeparator).equals(emptyString)) {
            throw new NoTimeException();
        }

        return new String[]{String.valueOf(beforeSeparator), String.valueOf(afterSeparator)};
    }

    /**
     *
     * @param userInput The input the user put in after an argument E.g. ("12")
     * @return An int that the user inputted
     * @throws DukeException If the user did not input an integer
     */
    public static int getInputNumber(String userInput) throws DukeException {

        assert userInput != null;

        try {
            return Integer.parseInt(userInput) - 1;
        } catch (NumberFormatException exception) {
            throw new NoNumberException();
        }
    }

    /**
     *
     * @param tasks A TaskList
     * @return A String representation of Tasks in the TaskList
     */
    public static String showTasks(TaskList tasks) {

        assert tasks != null;

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < tasks.numberOfTasks(); i++) {
            res.append(String.format("%d. %s%n\n", i + 1, tasks.getTask(i)));
        }
        return res.toString();
    }


}
