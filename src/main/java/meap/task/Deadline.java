package meap.task;

import meap.exception.DukeException;
import meap.util.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    public static final String SYMBOL = "D";
    public static final String COMMAND_REGEX = "deadline \\w[\\w, ]+\\w \\/by \\w[\\w,\\-, ]*";
    public static final String COMMAND_SYNTAX = "deadline <task> /by <eventTime>";
    public static final String COMMAND_EXAMPLE_CALL = "deadline project /by 01-01-2020 2359";

    private String userInputDeadline;
    private LocalDateTime dateTimeDeadline;
    private LocalDateTime dateTimeTaskCreation;
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("dd-MM-uuuu HHmm");

    /**
     * Factory method of Deadline class
     *
     * @param taskSummary task description
     * @param byDate
     * @return
     */
    public static Deadline of(String taskSummary, String byDate) {
        return new Deadline(taskSummary, byDate);
    }

    /**
     * Constructor for Deadline class.
     *
     * @param taskSummary task description
     * @param byDate String detailing date and time of event. Must follow syntax "dd-MM-uuuu HHmm"
     */
    public Deadline(String taskSummary, String byDate) {
        super(taskSummary);
        this.userInputDeadline = byDate;
        this.dateTimeTaskCreation = LocalDateTime.now();
        this.updateDateTimeDeadline(byDate);
    }

    /**
     * Factory method of Deadline class.
     * Takes in a String, parses it and returns the Deadline instance it represented
     *
     * @param storageLine string representing task
     * @return Deadline instance which the string represented
     */
    public static Deadline parse(String storageLine) {
        //example line: "D | 0 | work | 01-06-2020 1430"
        String[] args = storageLine.split(" \\| ");
        if (args.length != 4) {
            throw new IllegalArgumentException("storage line passed in doesnt have enough arguments");
        }
        Deadline loadedTask = new Deadline(args[2], args[3]);
        boolean completed = args[1].equals("1");
        if (completed) {
            loadedTask.markCompleted();
        }
        return loadedTask;
    }

    private void updateDateTimeDeadline(String byDate) {
        try {
            LocalDateTime parsedDateTime = Deadline.stringToLocalDateTime(byDate);
            // replace buggy code
            // prob: after deadline passed, when its in the store, loading it will cause an exception to be thrown
//            if (parsedDateTime.isBefore(dateTimeTaskCreation)) {
//                throw new IllegalArgumentException("Time of deadline is in the past!");
//            }
            this.dateTimeDeadline = parsedDateTime;
        } catch (DateTimeParseException e) {
            throw DukeException.of("date",
    String.format("Invalid date & time passed ('%s')\n\n", byDate) + Ui.commandSyntax("deadline"));
        } catch (IllegalArgumentException e) {
            throw DukeException.of("date",
    String.format("Invalid date & time passed ('%s')\n\n", byDate) + e.getMessage());
        }
    }

    private static LocalDateTime stringToLocalDateTime(String text) {
        return LocalDateTime.parse(text, Deadline.DATE_TIME_FORMATTER);
    }

    private static String localDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(Deadline.DATE_TIME_FORMATTER);
    }

    /**
     * Returns a string detailing the syntax of the ToDo command
     *
     * @return helper text of correct syntax
     */
//    public static String syntax() {
//        return String.format("deadline command syntax: \n" + "    '%s'\n" +
//                "Eg. 'deadline project /by 01-01-2020 2359'", Deadline.COMMAND_SYNTAX);
//    }

    /**
     * Converts an Task instance to a string to be stored.
     *
     * @return line of text detailing task details.
     */
    @Override
    public String toStorageFormat() {
        return String.format(
            "%s | %d | %s | %s",
            Deadline.SYMBOL, this.isCompleted() ? 1 : 0,this.getTaskSummary(), this.userInputDeadline
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline deadline = (Deadline) obj;
            boolean isDescSame = deadline.getTaskSummary().equals(this.getTaskSummary());
            boolean isDeadlineSame = deadline.dateTimeDeadline.equals(this.dateTimeDeadline);
            return isDescSame && isDeadlineSame;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format(
            "[%s][%s] %s (by: %s)",
            Deadline.SYMBOL,
            this.completeStatus(),
            this.getTaskSummary(),
            Deadline.localDateTimeToString(this.dateTimeDeadline)
        );
    }
}
