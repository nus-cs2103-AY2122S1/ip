import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Duke {
    // User-facing messages
    private static final String GREETING_MESSAGE = "Hello! I'm Duke. What can I do for you?";
    private static final String WELCOME_BACK_MESSAGE = "Welcome back! I'm Duke. " +
            "These are the tasks I recall from your last visit.\n\n%s";
    private static final String EXIT_MESSAGE = "Goodbye. Hope to see you again soon!";

    // User-facing error messages
    private static final String INVALID_COMMAND_MESSAGE = "Invalid command.";
    private static final String INVALID_TASK_NUMBER_MESSAGE = "Invalid task number.";

    // ResponsePrettifier settings
    private static final int INDENTATION_LEVEL = 4;
    private static final int SEPARATOR_LENGTH = 60;
    private static final String SEPARATOR = "_".repeat(SEPARATOR_LENGTH);

    public static void main(String[] args) {
        DukeResponsePrettifier prettifier = new DukeResponsePrettifier(INDENTATION_LEVEL, SEPARATOR);
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage("./data/tasks.txt");
        List<Task> loadedTasks = storage.loadTasks();
        TaskManager taskManager = new TaskManager(loadedTasks);
        if (loadedTasks.size() > 0) {
            prettifier.print(String.format(WELCOME_BACK_MESSAGE, taskManager));
        } else {
            prettifier.print(GREETING_MESSAGE);
        }
        while (scanner.hasNextLine()) {
            String inputLine = scanner.nextLine().trim();
            String firstWord = inputLine.split("\\s+")[0];
            try {
                Command command;
                try {
                    command = Command.valueOf(firstWord.toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new DukeException(INVALID_COMMAND_MESSAGE);
                }
                String inputLineWithoutCommand = inputLine.replace(firstWord, "").trim();
                command.validateArguments(inputLineWithoutCommand);
                switch (command) {
                case LIST:
                    prettifier.print(taskManager.toString());
                    break;
                case TODO:
                    String toDoName = inputLineWithoutCommand;
                    ToDo toDo = new ToDo(toDoName);
                    prettifier.print(taskManager.addTask(toDo));
                    storage.saveTasks(taskManager.toText());
                    break;
                case DEADLINE:
                    String[] deadlineDetails = inputLineWithoutCommand.split("\\s+/by\\s+", 2);
                    String deadlineName = deadlineDetails[0];
                    DukeDateTime deadlineDueDate=  DukeDateTime.parseUserInput(deadlineDetails[1]);
                    Deadline deadline = new Deadline(deadlineName, deadlineDueDate);
                    prettifier.print(taskManager.addTask(deadline));
                    storage.saveTasks(taskManager.toText());
                    break;
                case EVENT:
                    String[] eventDetails = inputLineWithoutCommand.split("\\s+/at\\s+", 2);
                    String eventName = eventDetails[0];
                    DukeDateTime eventTimestamp = DukeDateTime.parseUserInput(eventDetails[1]);
                    Event event = new Event(eventName, eventTimestamp);
                    prettifier.print(taskManager.addTask(event));
                    storage.saveTasks(taskManager.toText());
                    break;
                case DONE:
                    try {
                        int taskNumber = Integer.parseInt(inputLineWithoutCommand);
                        prettifier.print(taskManager.markTaskAsDone(taskNumber));
                        storage.saveTasks(taskManager.toText());
                    } catch (NumberFormatException e) {
                        // User provided an argument that is not parsable.
                        throw new DukeException(INVALID_TASK_NUMBER_MESSAGE);
                    }
                    break;
                case DELETE:
                    try {
                        int taskNumber = Integer.parseInt(inputLineWithoutCommand);
                        prettifier.print(taskManager.deleteTask(taskNumber));
                        storage.saveTasks(taskManager.toText());
                    } catch (NumberFormatException e) {
                        // User provided an argument that is not parsable.
                        throw new DukeException(INVALID_TASK_NUMBER_MESSAGE);
                    }
                    break;
                case BYE:
                    prettifier.print(EXIT_MESSAGE);
                    scanner.close();
                    return;
                }
            } catch (DukeException e) {
                prettifier.print(e.getMessage());
            }
        }
    }

}
