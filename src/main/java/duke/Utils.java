package duke;

import duke.exception.DukeException;
import duke.exception.IncorrectFormatException;
import duke.exception.NoNumberException;
import duke.exception.NoTimeException;

import java.util.StringJoiner;

public class Utils {

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

    public static int getInputNumber(String userInput) throws DukeException {

        assert userInput != null;

        try {
            return Integer.parseInt(userInput) - 1;
        } catch (NumberFormatException exception) {
            throw new NoNumberException();
        }
    }

    public static String showTasks(TaskList tasks) {

        assert tasks != null;

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < tasks.numberOfTasks(); i++) {
            res.append(String.format("%d. %s%n\n", i + 1, tasks.getTask(i)));
        }
        return res.toString();
    }


}
