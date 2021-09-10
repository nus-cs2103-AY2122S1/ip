package kermit.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import kermit.KermitException;
import kermit.tasks.DateDependentTask;

public abstract class AddDateDependentTaskCommand extends AddTaskCommand {
    protected AddDateDependentTaskCommand(DateDependentTask task, String description) throws KermitException {
        super(task, description);
    }

    /**
     * Parse dates in form dd-mm-yyyy to yyyy-mm-dd, the format LocalDate is compatible with.
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
