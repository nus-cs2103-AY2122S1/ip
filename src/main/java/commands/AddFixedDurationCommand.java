package commands;

import exceptions.MorganException;
import tasks.FixedDurationTask;


public class AddFixedDurationCommand extends AddCommand {
    public static final String KEYWORD = "fixed";
    public static final String DELIMITER = "/for";
    public static final String INPUT_FORMAT = String.format("\t%s [task] %s "
            + "[duration (in hours)]", KEYWORD, DELIMITER);
    private static final int TASK_INDEX = 0;
    private static final int DURATION_INDEX = 1;
    private static final int INPUT_PARAMETERS = 2;
    private static final String INPUT_FORMAT_ERROR = String.format("Please "
            + "ensure your input is in the following format:\n" + INPUT_FORMAT);
    private static final String DURATION_FORMAT_ERROR = String.format("Please "
            + "provide a valid duration (in hours).");

    /**
     * Constructor for AddDeadlineCommand.
     * @param userInput The input string entered by the user.
     * @throws MorganException If input format is invalid.
     */
    public AddFixedDurationCommand(String userInput) throws MorganException {
        assert userInput != null;
        boolean hasDelimiter = userInput.contains(DELIMITER);
        if (!hasDelimiter) {
            throw new MorganException(INPUT_FORMAT_ERROR);
        }

        String[] inputData = userInput.substring(KEYWORD.length()).trim().split(DELIMITER);
        boolean isValidInput = (inputData.length == INPUT_PARAMETERS);
        if (!isValidInput) {
            throw new MorganException(INPUT_FORMAT_ERROR);
        }

        String taskName = inputData[TASK_INDEX].trim();
        String duration = inputData[DURATION_INDEX].trim();
        try {
            this.task = new FixedDurationTask(taskName, duration);
        } catch (NumberFormatException e) {
            throw new MorganException(DURATION_FORMAT_ERROR);
        }
    }
}
