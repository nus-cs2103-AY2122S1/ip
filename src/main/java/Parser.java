public class Parser {
    public static Command parse(String answer) throws DukeException {
        String[] parts = answer.split(" ");
        String command = parts[0];
        String taskDetails = "";
        if (answer.contains(" ")) {
            taskDetails = answer.substring(answer.indexOf(" ") + 1);
        }
        switch (command) {
            case "done":
                int taskIndex = getTaskIndex(answer);
                return new DoneCommand(taskIndex);
            case "delete":
                taskIndex = getTaskIndex(answer);
                return new DeleteCommand(taskIndex);
            case "list":
                return new ListCommand();
            case "todo":
                Todo todo = parseTodo(taskDetails);
                return new AddTaskCommand(todo);
            case "event":
                Event event = parseEvent(taskDetails);
                return new AddTaskCommand(event);
            case "deadline":
                Deadline deadline = parseDeadline(taskDetails);
                return new AddTaskCommand(deadline);
            case "bye":
                return new ByeCommand();
            default:
                throw new DukeException("Unknown command.");
        }
    }
    
    private static void checkEmptyTaskDetails(String taskDetails) throws DukeException {
        if (taskDetails.isEmpty()) {
            throw new DukeException("Task details cannot be empty");
        }
    }
    
    private static Todo parseTodo(String taskDetails) throws DukeException {
        checkEmptyTaskDetails(taskDetails);
        return new Todo(taskDetails, false);
    }

    private static Event parseEvent(String taskDetails) throws DukeException {
        checkEmptyTaskDetails(taskDetails);
        String[] parts = taskDetails.split(" /at ");
        if (parts.length < 2) {
            throw new DukeException("Event descriptions must contain /at [dd-mm-yyyy hh:mm]");
        }
        String description = parts[0];
        String at = parts[1];
        return new Event(description, at, false);
    }

    private static Deadline parseDeadline(String taskDetails) throws DukeException {
        checkEmptyTaskDetails(taskDetails);
        String[] parts = taskDetails.split(" /by ");
        if (parts.length < 2) {
            throw new DukeException("Deadline descriptions must contain /by [dd-mm-yyyy hh:mm]");
        }
        String description = parts[0];
        String by = parts[1];
        return new Deadline(description, by, false);
    }

    protected static int getTaskIndex(String answer) throws DukeException {
        String taskNo = answer.substring(answer.indexOf(" ") + 1);
        try {
            int taskIndex = Integer.parseInt(taskNo) - 1;
            if (taskIndex < 0) {
                throw new DukeException("Invalid task number. Task number should be positive");
            }
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number. Sample input with correct format: [command] [taskNo]"
                    + " eg. 'done 2'");
        }
    }
}
