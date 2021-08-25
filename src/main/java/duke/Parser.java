package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Parser is a static class that interprets the users inputs.
 *
 * @author meerian
 */
public class Parser {
    /**
     * Represents the DateValidators the parser uses to interpret users inputs.
     */
    private static final DateValidator ISOLocalDateValidator =
            new DateValidator(DateTimeFormatter.ISO_LOCAL_DATE);
    private static final DateValidator LocalDateValidator =
            new DateValidator(DateTimeFormatter.ofPattern("MMM dd yyyy"));

    /**
     * Interpret what task the user is trying to create.
     *
     * @param str the description of the task.
     * @param check used to verify what task to create.
     * @return the relevant task from the strings provided.
     */
    public static Task check(String str, String check) throws DukeException {
        int partition = str.indexOf(check);
        if (partition < 0 || partition + check.length() > str.length()) {
            throw new DukeException("☹ OOPS!!! The task is formatted wrongly.");
        }
        String str1 = str.substring(0, partition);
        String str2 = str.substring(partition + check.length());
        LocalDate date = null;

        if (ISOLocalDateValidator.isValid(str2)) {
            date = LocalDate.parse(str2);
        } else if (LocalDateValidator.isValid(str2)) {
            date = LocalDate.parse(str2, DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }

        switch (check) {
            case "/by ":
                if (date == null) {
                    return new Deadline(str1, str2);
                } else {
                    return new Deadline(str1, date);
                }

            case " (by: ":
                return new Deadline(str1, str2);

            case "/at ":
                if (date == null) {
                    return new Event(str1, str2);
                } else {
                    return new Event(str1, date);
                }

            case " (at: ":
                return new Event(str1, str2);

            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Interpret the lines in the save file and recreate the correct tasks.
     *
     * @param string the description of the task to recreate.
     * @return the relevant task from the string provided.
     */
    public static Task read(String string) throws DukeException {
        Task task;
        String taskType = string.substring(0, 3);
        char doneCheck = string.charAt(4);

        switch (taskType) {
            case "[T]":
                String tdLabel = string.substring(7);
                task = new Todo(tdLabel);
                break;

            case "[D]":
                String dlLabel = string.substring(7, string.length() - 1);
                task = Parser.check(dlLabel, " (by: ");
                break;

            case "[E]":
                String eLabel = string.substring(7, string.length() - 1);
                task = Parser.check(eLabel, " (at: ");
                break;

            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        if (doneCheck == 'X') {
            task.Done();
        }
        return task;

    }
}
