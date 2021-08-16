import java.util.Scanner;

public class Duke {
    // User commands
    private static final String LIST_COMMAND = "list";
    private static final String MARK_TASK_AS_DONE_COMMAND = "done";
    private static final String DELETE_TASK_COMMAND = "delete";
    private static final String EXIT_COMMAND = "bye";

    private static final String ADD_TODO_COMMAND = "todo";
    private static final String ADD_DEADLINE_COMMAND = "deadline";
    private static final String DEADLINE_COMMAND_DELIMITER = " /by ";
    private static final String ADD_EVENT_COMMAND = "event";
    private static final String EVENT_COMMAND_DELIMITER = " /at ";

    // User-facing messages
    private static final String GREETING_MESSAGE = "Hello! I'm Duke. What can I do for you?";
    private static final String EXIT_MESSAGE = "Goodbye. Hope to see you again soon!";

    // User-facing error messages
    private static final String INVALID_COMMAND_MESSAGE = "Invalid command.";
    private static final String INVALID_TASK_NUMBER_MESSAGE = "Invalid task number.";
    private static final String MARK_DONE_BAD_ARGS_MESSAGE = "Invalid use of the 'done' command.\n\n" +
            "To mark a task as done, use 'done <task-number>'.";
    private static final String DELETE_TASK_BAD_ARGS_MESSAGE = "Invalid use of the 'delete' command.\n\n" +
            "To delete a task, use 'delete <task-number>'.";
    private static final String TODO_BAD_ARGS_MESSAGE = "Invalid use of the 'todo' command.\n\n" +
            "To add a new todo, use 'todo <name>'.";
    private static final String DEADLINE_BAD_ARGS_MESSAGE = "Invalid use of the 'deadline' command.\n\n" +
            "To add a new deadline, use 'deadline <name> /by <due-date>'.";
    private static final String EVENT_BAD_ARGS_MESSAGE = "Invalid use of the 'event' command.\n\n" +
            "To add a new event, use 'event <name> /at <event-timestamp>'.";

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
            String inputLine = scanner.nextLine().trim();
            String command = inputLine.split(" ")[0];
            String inputLineWithoutCommand = inputLine.replace(command, "").trim();
            try {
                switch (command) {
                case LIST_COMMAND:
                    prettifier.print(taskManager.toString());
                    break;
                case MARK_TASK_AS_DONE_COMMAND:
                    if (inputLineWithoutCommand.isEmpty()) {
                        throw new DukeException(MARK_DONE_BAD_ARGS_MESSAGE);
                    }
                    try {
                        int taskNumber = Integer.parseInt(inputLineWithoutCommand);
                        prettifier.print(taskManager.markTaskAsDone(taskNumber));
                    } catch (NumberFormatException e) {
                        // User provided an argument that is not parsable.
                        throw new DukeException(INVALID_TASK_NUMBER_MESSAGE);
                    }
                    break;
                case DELETE_TASK_COMMAND:
                    if (inputLineWithoutCommand.isEmpty()) {
                        throw new DukeException(DELETE_TASK_BAD_ARGS_MESSAGE);
                    }
                    try {
                        int taskNumber = Integer.parseInt(inputLineWithoutCommand);
                        prettifier.print(taskManager.deleteTask(taskNumber));
                    } catch (NumberFormatException e) {
                        throw new DukeException(INVALID_TASK_NUMBER_MESSAGE);
                    }
                    break;
                case ADD_TODO_COMMAND:
                    String toDoName = inputLineWithoutCommand;
                    if (toDoName.isEmpty()) {
                        throw new DukeException(TODO_BAD_ARGS_MESSAGE);
                    }
                    ToDo toDo = new ToDo(toDoName);
                    prettifier.print(taskManager.addTask(toDo));
                    break;
                case ADD_DEADLINE_COMMAND:
                    String[] deadlineDetails = inputLineWithoutCommand.split(DEADLINE_COMMAND_DELIMITER);
                    if (deadlineDetails.length < 2) {
                        throw new DukeException(DEADLINE_BAD_ARGS_MESSAGE);
                    }
                    String deadlineName = deadlineDetails[0];
                    String deadlineDueDate = deadlineDetails[1];
                    Deadline deadline = new Deadline(deadlineName, deadlineDueDate);
                    prettifier.print(taskManager.addTask(deadline));
                    break;
                case ADD_EVENT_COMMAND:
                    String[] eventDetails = inputLineWithoutCommand.split(EVENT_COMMAND_DELIMITER);
                    if (eventDetails.length < 2) {
                        throw new DukeException(EVENT_BAD_ARGS_MESSAGE);
                    }
                    String eventName = eventDetails[0];
                    String eventTimestamp = eventDetails[1];
                    Event event = new Event(eventName, eventTimestamp);
                    prettifier.print(taskManager.addTask(event));
                    break;
                case EXIT_COMMAND:
                    prettifier.print(EXIT_MESSAGE);
                    scanner.close();
                    return;
                default:
                    throw new DukeException(INVALID_COMMAND_MESSAGE);
                }
            } catch (DukeException e) {
                prettifier.print(e.getMessage());
            }

        }
    }

}
