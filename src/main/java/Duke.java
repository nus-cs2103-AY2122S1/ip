import java.util.Scanner;

public class Duke {
    // User commands
    private static final String LIST_COMMAND = "list";
    private static final String MARK_TASK_AS_DONE_COMMAND = "done";
    private static final String EXIT_COMMAND = "bye";

    private static final String ADD_TODO_COMMAND = "todo";
    private static final String ADD_DEADLINE_COMMAND = "deadline";
    private static final String DEADLINE_COMMAND_DELIMITER = "/by";
    private static final String ADD_EVENT_COMMAND = "event";
    private static final String EVENT_COMMAND_DELIMITER = "/at";

    // User-facing messages
    private static final String GREETING_MESSAGE = "Hello! I'm Duke. What can I do for you?";
    private static final String EXIT_MESSAGE = "Goodbye. Hope to see you again soon!";

    // User-facing error messages
    private static final String INVALID_COMMAND_MESSAGE = "Invalid command.";
    private static final String MISSING_TASK_NUMBER_MESSAGE =
            "You have to let me know which task to be marked as completed.";
    private static final String INVALID_TASK_NUMBER_MESSAGE =
            "You need to give me a valid number to mark as completed.";
    private static final String MISSING_TASK_NAME_MESSAGE = "You have to let me know what the name of the task is.";
    private static final String MISSING_DEADLINE_DUE_DATE_MESSAGE = "You have to let me know when the deadline is by.";
    private static final String MISSING_EVENT_TIMESTAMP_MESSAGE = "You have to let me know when the event is.";

    // ResponsePrettifier settings
    private static final int INDENTATION_LEVEL = 4;
    private static final int SEPARATOR_LENGTH = 60;
    private static final String SEPARATOR = "_".repeat(SEPARATOR_LENGTH);

    public static void main(String[] args) {
        DukeResponsePrettifier prettifier = new DukeResponsePrettifier(INDENTATION_LEVEL, SEPARATOR);
        prettifier.print(GREETING_MESSAGE);

        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        while (scanner.hasNextLine()) {
            String inputLine = scanner.nextLine();
            String[] inputArguments = inputLine.split(" ");
            String command = inputArguments[0];
            switch (command) {
            case LIST_COMMAND:
                prettifier.print(taskManager.toString());
                break;
            case MARK_TASK_AS_DONE_COMMAND:
                if (inputArguments.length < 2) {
                    // Index of task to be marked as done is not provided
                    prettifier.print(MISSING_TASK_NUMBER_MESSAGE);
                    break;
                }
                try {
                    int taskNumber = Integer.parseInt(inputArguments[1]);
                    prettifier.print(taskManager.markTaskAsDone(taskNumber));
                } catch (NumberFormatException e) {
                    // User provided an argument that is not parsable.
                    prettifier.print(INVALID_TASK_NUMBER_MESSAGE);
                }
                break;
            case ADD_TODO_COMMAND:
                String toDoName = inputLine.substring(ADD_TODO_COMMAND.length() + 1).trim();
                if (toDoName.isEmpty()) {
                    // Name was not provided
                    prettifier.print(MISSING_TASK_NAME_MESSAGE);
                    break;
                }
                ToDo toDo = new ToDo(toDoName);
                prettifier.print(taskManager.addTask(toDo));
                break;
            case ADD_DEADLINE_COMMAND:
                String[] deadlineDetails = inputLine.
                        substring(ADD_DEADLINE_COMMAND.length() + 1).
                        split(DEADLINE_COMMAND_DELIMITER);
                if (deadlineDetails.length < 2) {
                    // "/by" was not provided, or an empty string was provided after "/by"
                    prettifier.print(MISSING_DEADLINE_DUE_DATE_MESSAGE);
                    break;
                }
                String deadlineName = deadlineDetails[0].trim();
                if (deadlineName.isEmpty()) {
                    // Name of Deadline was not provided
                    prettifier.print(MISSING_TASK_NAME_MESSAGE);
                    break;
                }
                String deadlineDueDate = deadlineDetails[1].trim();
                if (deadlineDueDate.isEmpty()) {
                    // Due Date is empty string after trimming whitespace
                    prettifier.print(MISSING_DEADLINE_DUE_DATE_MESSAGE);
                    break;
                }
                Deadline deadline = new Deadline(deadlineName, deadlineDueDate);
                prettifier.print(taskManager.addTask(deadline));
                break;
            case ADD_EVENT_COMMAND:
                String[] eventDetails = inputLine.
                        substring(ADD_EVENT_COMMAND.length() + 1).
                        split(EVENT_COMMAND_DELIMITER);
                if (eventDetails.length < 2) {
                    // "/at" was not provided, or an empty string was provided after "/at"
                    prettifier.print(MISSING_EVENT_TIMESTAMP_MESSAGE);
                    break;
                }
                String eventName = eventDetails[0].trim();
                if (eventName.isEmpty()) {
                    // Name of Event was not provided
                    prettifier.print(MISSING_TASK_NAME_MESSAGE);
                    break;
                }
                String eventTimestamp = eventDetails[1].trim();
                if (eventTimestamp.isEmpty()) {
                    // Timestamp is empty string after trimming whitespace
                    prettifier.print(MISSING_EVENT_TIMESTAMP_MESSAGE);
                    break;
                }
                Event event = new Event(eventName, eventTimestamp);
                prettifier.print(taskManager.addTask(event));
                break;
            case EXIT_COMMAND:
                prettifier.print(EXIT_MESSAGE);
                scanner.close();
                return;
            default:
                prettifier.print(INVALID_COMMAND_MESSAGE);
                break;
            }
        }
    }

}
