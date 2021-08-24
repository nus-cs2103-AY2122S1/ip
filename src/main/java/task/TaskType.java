package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;

/**
 * Different task types, as well as
 * functions related to the differentiation
 * and creation of tasks
 */
public enum TaskType {
    TODO,
    EVENT,
    DEADLINE;

    /**
     * Based on the type provided, return the corresponding class from info provided by Matcher
     *
     * @param input Groups of information needed for the task
     * @param type Type of task to use
     * @return A task.Task that matches that of its task.TaskType
     */
    public static Task getNewTask(Matcher input, TaskType type) {
        try {
            switch(type) {
                case TODO:
                    return new TaskTodo(input.group(1), false);
                case DEADLINE:
                    // Description, date, time
                    return new TaskDeadline(input.group(1), getDate(input.group(2)), input.group(3), false);
                case EVENT:
                    // Description, date, time
                    return new TaskEvent(input.group(1), getDate(input.group(2)), input.group(3), false);
                default: return null;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Please enter a valid date! :(");
            return null;
        }
    }

    /**
     * Converts a string to a date
     *
     * @param date String to convert
     * @return LocalDate object
     * @throws DateTimeParseException Thrown if error in parsing date
     */
    public static LocalDate getDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(date, formatter);
    }
}
