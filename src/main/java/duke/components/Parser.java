package duke.components;

import java.time.format.DateTimeFormatter;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a parser that will make sense of user input.
 */
public class Parser {
    private final String end;
    private final String display;
    private final String markDone;
    private final String deleteTask;
    private final String taskTodo;
    private final String taskDdl;
    private final String taskEve;
    private final DateTimeFormatter formatter;

    public Parser() {
        this.end = "bye";
        this.display = "list";
        this.markDone = "done";
        this.deleteTask = "delete";
        this.taskTodo = "todo";
        this.taskDdl = "deadline";
        this.taskEve = "event";
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm", Locale.ENGLISH);
    }

    /**
     * Checks if user input is "bye".
     *
     * @param input The user input.
     * @return Boolean that represents whether the user input is "bye".
     */
    public boolean isEnd(String input) {
        return input.equals(end);
    }

    /**
     * Checks if user input is "list".
     *
     * @param input The user input.
     * @return Boolean that represents whether the user input is "list".
     */
    public boolean isDisplay(String input) {
        return input.equals(display);
    }

    /**
     * Checks if user input is "done".
     *
     * @param input The user input.
     * @return Boolean that represents whether the user input is "done".
     */
    public boolean isDone(String input) {
        return input.equals(markDone);
    }

    /**
     * Checks if user input is "delete".
     *
     * @param input The user input.
     * @return Boolean that represents whether the user input is "delete".
     */
    public boolean isDelete(String input) {
        return input.equals(deleteTask);
    }

    /**
     * Checks if user input is "todo".
     *
     * @param input The user input.
     * @return Boolean that represents whether the user input is "todo".
     */
    public boolean isTodo(String input) {
        return input.equals(taskTodo);
    }

    /**
     * Checks if user input is "deadline".
     *
     * @param input The user input.
     * @return Boolean that represents whether the user input is "deadline".
     */
    public boolean isDeadline(String input) {
        return input.equals(taskDdl);
    }

    /**
     * Checks if user input is "event".
     *
     * @param input The user input.
     * @return Boolean that represents whether the user input is "event".
     */
    public boolean isEvent(String input) {
        return input.equals(taskEve);
    }

    /**
     * Returns the formatter used for format date and time.
     *
     * @return The DateTimeFormatter used in Duke.
     */
    public DateTimeFormatter getFormatter() {
        return this.formatter;
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
}
