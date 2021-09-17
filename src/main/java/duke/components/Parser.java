package duke.components;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a parser that will make sense of user input.
 */
public class Parser {
    private static final String END = "bye";
    private static final String DISPLAY = "list";
    private static final String MARK_DONE = "done";
    private static final String DELETE_TASK = "delete";
    private static final String TASK_TODO = "todo";
    private static final String TASK_DEADLINE = "deadline";
    private static final String TASK_EVENT = "event";
    private static final String FIND = "find";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(
            "dd/MM/yyyy kk:mm", Locale.ENGLISH);

    public Parser() {
    }

    /**
     * Checks if user input is "bye".
     *
     * @param input The user input.
     * @return Boolean that represents whether the user input is "bye".
     */
    public boolean isEnd(String input) {
        return input.equals(END);
    }

    /**
     * Checks if user input is "list".
     *
     * @param input The user input.
     * @return Boolean that represents whether the user input is "list".
     */
    public boolean isDisplay(String input) {
        return input.equals(DISPLAY);
    }

    /**
     * Checks if user input is "done".
     *
     * @param input The user input.
     * @return Boolean that represents whether the user input is "done".
     */
    public boolean isDone(String input) {
        return input.equals(MARK_DONE);
    }

    /**
     * Checks if user input is "delete".
     *
     * @param input The user input.
     * @return Boolean that represents whether the user input is "delete".
     */
    public boolean isDelete(String input) {
        return input.equals(DELETE_TASK);
    }

    /**
     * Checks if user input is "todo".
     *
     * @param input The user input.
     * @return Boolean that represents whether the user input is "todo".
     */
    public boolean isTodo(String input) {
        return input.equals(TASK_TODO);
    }

    /**
     * Checks if user input is "deadline".
     *
     * @param input The user input.
     * @return Boolean that represents whether the user input is "deadline".
     */
    public boolean isDeadline(String input) {
        return input.equals(TASK_DEADLINE);
    }

    /**
     * Checks if user input is "event".
     *
     * @param input The user input.
     * @return Boolean that represents whether the user input is "event".
     */
    public boolean isEvent(String input) {
        return input.equals(TASK_EVENT);
    }

    /**
     * Returns the formatter used for format date and time.
     *
     * @return The DateTimeFormatter used in Duke.
     */
    public DateTimeFormatter getFormatter() {
        return FORMATTER;
    }

    /**
     * Checks if user input is "done".
     *
     * @param input The user input.
     * @return Boolean that represents whether the user input is "done".
     */
    public boolean isMarkDoneCommand(String input) {
        Pattern pattern = Pattern.compile("done\\s\\d+");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    /**
     * Checks if user input is "delete".
     *
     * @param input The user input.
     * @return Boolean that represents whether the user input is "delete".
     */
    public boolean isDeleteTaskCommand(String input) {
        Pattern patternDelete = Pattern.compile("delete\\s\\d+");
        Matcher matcherDelete = patternDelete.matcher(input);
        return matcherDelete.find();
    }

    /**
     * Checks if user input is "find".
     *
     * @param input The user input.
     * @return Boolean that represents whether the user input is "find".
     */
    public boolean isFindTask(String input) {
        if (input.length() >= 5) {
            return input.substring(0, 4).equals(FIND);
        }
        return false;
    }
}
