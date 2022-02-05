package duke.tasks;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.regex.Pattern;

import duke.exceptions.UserInputError;

/**
 * The abstract Task class representing a task.
 */
public abstract class Task {

    /**
    *  Enum class for different class types.
    */
    public enum Type {
        TODO, EVENT, DEADLINE,
    }

    private static final String DELIMITER = " ~#~ ";

    private final String description;
    private final Type type;
    private boolean isDone;

    /**
     * Constructor for a Task object.
     *
     * @param description The description of the task.
     * @param type The enum type of the task.
     * @param done The boolean value of whether task is completed.
     */
    protected Task(String description, Type type, boolean done) {
        this.description = description;
        this.type = type;
        this.isDone = done;
    }

    /**
     * Get LocalDate object from a date string that can be a natural date or normal date.
     * Parse date String to identify natural dates, if existent, and convert them to YYYY-MM-DD format.
     * Otherwise, parse String and throw an error if date String is in an invalid format.
     *
     * @param date String containing the input task date either in natural date format or YYYY-MM-DD
     * @return Formatted date String of the task
     */
    protected LocalDate getDateFromString(String date) {
        LocalDate today = LocalDate.now();

        switch(date) {
        case "mon":
            return today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        case "tues":
            return today.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        case "wed":
            return today.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
        case "thurs":
            return today.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        case "fri":
            return today.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        case "sat":
            return today.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        case "sun":
            return today.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        case "tmr":
            return today.plusDays(1);
        case "today":
            return today;
        default:
            return LocalDate.parse(date);
        }
    }

    /**
     * Checks the format and validity of given time String.
     * Throws an error if time String is invalid.
     *
     * @param time String of time that is being checked.
     * @throws UserInputError Throws error if time String is not valid.
     */
    public void checkTimeFormat(String time) throws UserInputError {
        String trimmed = time.trim();

        if (trimmed.length() != 4) { //checks that string adheres to 24h format
            throw new UserInputError("Invalid time input. Please ensure it is in 24h format");
        }

        int hour = Integer.parseInt(trimmed.substring(0, 2));
        int min = Integer.parseInt(trimmed.substring(2, 4));

        if (hour < 0 || hour > 23 || min < 0 || min > 59) { //checks hour and min are valid (i.e. 1260 is rejected)
            throw new UserInputError("Your hour/minute input is invalid. Please check and try again!");
        }
    }

    /**
     * Creates a new task according task type.
     * Throws a UserInputError if Task type is invalid.
     *
     * @param input String containing task details.
     * @param type The enum type of the task.
     * @return A Task object.
     * @throws UserInputError Throws error with invalid task type.
     */
    public static Task createTask(String input, Type type) throws UserInputError {
        String[] details;

        switch (type) {
        case TODO:
            return new Todo(input, false);
        case EVENT:
            details = separateDetails(input, "/at");
            return new Event(details[0], details[1], false);
        case DEADLINE:
            details = separateDetails(input, "/by");
            return new Deadline(details[0], details[1], false);
        default:
            throw new UserInputError("Wrong task type");
        }
    }

    /**
     * Return a Task object by converting from a task String retrieved from database.
     * Throws a UserInputError if task String is invalid.
     *
     * @param dataString String containing Task details.
     * @return A Task object with task type and details.
     * @throws UserInputError Throws error with invalid task String format.
     */
    public static Task stringToTask(String dataString) throws UserInputError {
        Task newTask;
        String[] infoArr = dataString.split(DELIMITER);

        String type = infoArr[0];
        boolean isDone = infoArr[1].equals("1");
        String desc = infoArr[2];
        String date = infoArr.length == 3 ? "" : infoArr[3];

        switch (type) {
        case "T":
            newTask = new Todo(desc, isDone);
            break;
        case "D":
            newTask = new Deadline(desc, date, isDone);
            break;
        case "E":
            newTask = new Event(desc, date, isDone);
            break;
        default:
            newTask = null;
        }

        return newTask;
    }

    /**
     * Returns an array containing the task description and datetime.
     * Throws a UserInputError if details are incomplete.
     *
     * @param str String of details.
     * @param key Delimiter.
     * @return Array of description and datetime.
     * @throws UserInputError Throws error with incomplete details.
     */
    private static String[] separateDetails(String str, String key) throws UserInputError {
        if (str.split(key).length <= 1) {
            throw new UserInputError(
                    "Oops! Please use the correct format with " + key + " to indicate datetime");
        }
        return str.split(key);
    }

    /**
     * Convert Task object to a task String.
     *
     * @return String writeable to database.
     */
    public String convertTaskToString() {
        String type;
        String done = this.isDone ? "1" : "0";

        switch(this.type) {
        case TODO:
            type = "T";
            break;
        case EVENT:
            type = "E";
            break;
        case DEADLINE:
            type = "D";
            break;
        default:
            throw new IllegalArgumentException("Task type enums inconsistent");
        }

        return String.format(
            "%s%s%s%s%s%s",
            type,
                DELIMITER,
            done,
                DELIMITER,
            this.description,
                DELIMITER
        );
    }

    /**
     * Return a formatted icon to symbolise if task is completed.
     *
     * @return String icon representing completeness.
     */
    private String getStatusIcon() {
        return (isDone ? "[X]" : "[  ]"); // mark done task with X
    }

    /**
     * Check if task description contains the necessary keyword for find function.
     *
     * @param key keyword user is looking for.
     * @return Boolean if this task contains keyword.
     */
    public boolean descContains(String key) {
        String regex = ".*\\b" + Pattern.quote(key.toLowerCase()) + "\\b.*";
        return description.toLowerCase().matches(regex);
    }

    /**
     * Mark the task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Check if task is completed.
     *
     * @return Boolean value of task completeness.
     */
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
