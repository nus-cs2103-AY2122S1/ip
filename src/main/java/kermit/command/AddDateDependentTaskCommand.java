package kermit.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import kermit.KermitException;
import kermit.tasks.DateDependentTask;

/**
 * AddDateDependentTaskCommand is an abstract class for DateDependentTasks.
 */
public abstract class AddDateDependentTaskCommand extends AddTaskCommand {
    /**
     * Constructs AddDateDependentTaskCommand.
     *
     * @param task task that has been created.
     * @param description description of task.
     * @throws KermitException if task is not valid.
     */
    protected AddDateDependentTaskCommand(DateDependentTask task, String description) throws KermitException {
        super(task, description);
    }

    /**
     * Parses dates in form dd-mm-yyyy.
     * Parses date from dd-mm-yyyy to  yyyy-mm-dd, the format LocalDate is compatible with.
     *
     * @param dateString  date string in form dd-mm-yyyy.
     * @return LocalDate object.
     * @throws KermitException if unable to parse string to date.
     */
    protected static LocalDate parseDate(String dateString) throws KermitException {
        String[] components = dateString.split("-");
        try {
            String day = components[0];
            String month = components[1];
            String year = components[2];
            LocalDate parsedDate = LocalDate.parse(String.join("-", year, month, day));
            return parsedDate;
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new KermitException("That is an invalid date!");
        }
    }
}
