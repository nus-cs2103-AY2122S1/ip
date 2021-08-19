public class AddEventCommand extends AddCommand {
    private static final String DELIMITER = "/at";
    public static final String KEYWORD = "event";
    private static final int TASK_INDEX = 0;
    private static final int DATETIME_INDEX = 1;

    public AddEventCommand (String userInput) {
        // TODO: Input validation especially for dateTime
        String[] inputData = userInput.substring(KEYWORD.length()).trim().split(DELIMITER);
        String taskName = inputData[TASK_INDEX].trim();
        String dateTime = inputData[DATETIME_INDEX].trim();
        this.task = new EventTask(taskName, dateTime);
    }
}
