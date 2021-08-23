package commands;
import tasks.DeadlineTask;
import tasks.EventTask;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends AddCommand {
    private static final String DELIMITER = "/at";
    public static final String KEYWORD = "event";
    private static final int TASK_INDEX = 0;
    private static final int DATETIME_INDEX = 1;

    public AddEventCommand (String userInput) throws DukeException {
        // TODO: dateTime validation
        String inputFormat = String.format("\t\"%s [task] %s [mm-dd-yyyy hh:mm]\"", KEYWORD, DELIMITER);

        // Check whether input contains delimiter
        boolean hasDelimiter = userInput.contains(DELIMITER);
        if (!hasDelimiter) {
            throw new DukeException("OOPS!!! " +
                    "Please ensure your input is in the following format:\n" + inputFormat);

        }
        String[] inputData = userInput.substring(KEYWORD.length()).trim().split(DELIMITER);

        // Check whether input contains task and date/time
        boolean isValidInput = (inputData.length == 2);
        if (!isValidInput) {
            throw new DukeException("OOPS!!! " +
                    "Please ensure your input is in the following format:\n" + inputFormat);
        }

        String taskName = inputData[TASK_INDEX].trim();
        String dateTime = inputData[DATETIME_INDEX].trim();
        try {
            this.task = new EventTask(taskName, dateTime);
        } catch(DateTimeParseException e) {
            throw new DukeException("OOPS!!! " +
                    "Please ensure your input is in the following format:\n" + inputFormat);
        }
    }
}
