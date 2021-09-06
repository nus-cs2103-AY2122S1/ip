package duke.tasks;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.regex.Pattern;

import duke.exceptions.UserInputError;
import duke.util.Ui;

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

    private static final String SEPARATE = " ~#~ ";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

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
     * Creates a new task according task type.
     *
     * @param input String containing task details.
     * @param type The enum type of the task.
     * @return A Task object.
     * @throws UserInputError Throws error with bad user input.
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
     * Convert String equivalent of a task from database to a Task object.
     *
     * @param dataString String containing Task details.
     * @return A Task object with task type and details.
     */
    public static Task stringToTask(String dataString) {
        Task newTask = null;
        String[] infoArr = dataString.split(SEPARATE);

        String type = infoArr[0];
        boolean isDone = infoArr[1].equals("1");
        String desc = infoArr[2];
        String date = infoArr.length == 3 ? "" : infoArr[3];

        switch (type) {
        case "T":
            newTask = new Todo(desc, isDone);
            break;
        case "D":
            try {
                newTask = new Deadline(desc, date, isDone);
            } catch (UserInputError e) {
                Ui.formatOutput(e.getMessage());
            }
            break;
        case "E":
            try {
                newTask = new Event(desc, date, isDone);
            } catch (UserInputError e) {
                Ui.formatOutput(e.getMessage());
            }
            break;
        default:
            newTask = null;
        }

        return newTask;
    }

    /**
     * Separate input into task description and datetime.
     *
     * @param str String of details.
     * @param key Delimiter.
     * @return Array of description and datetime.
     * @throws UserInputError Throws error with bad user input.
     */
    private static String[] separateDetails(String str, String key) throws UserInputError {
        if (str.split(key).length <= 1) {
            throw new UserInputError(
                    "Oops! Please use the correct format with " + key + " to indicate datetime");
        }
        return str.split(key);
    }

    /**
     * Convert Task object to a String form for database.
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
            SEPARATE,
            done,
            SEPARATE,
            this.description,
            SEPARATE
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
     * Mark task as done.
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
