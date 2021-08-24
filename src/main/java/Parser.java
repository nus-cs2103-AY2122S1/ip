import java.time.LocalDate;

/**
 * A class for making sense of the user command
 * Bridges communication between the user and the tasks
 */
public class Parser {
    public static boolean isRunning = true;
    private static TaskList tasklist;
    private static String command;
    private static String[] commandArr;
    /**
     * Handles user input regarding tasks
     *
     * @param userString String that users enter
     */
    public static void handle(String userString, TaskList tasklist) throws DukeException {
        Parser.command = userString;
        Parser.tasklist = tasklist;
        String[] arr = userString.split(" ");
        String firstWord = arr[0];
        Parser.commandArr = arr;

        switch (firstWord) {
        case "bye":
            UI.bye();
            isRunning = true;
            break;
        case "list":
            tasklist.list();
            break;
        case "done":
            handleDone();
            break;
        case "todo":
            handleTodo();
            break;
        case "deadline":
            handleDeadline();
            break;
        case "event":
            handleEvent();
            break;
        case "delete":
            handleDelete();
            break;
        default:
            throw new InvalidCommandException("Invalid command");
        }
    }

    public static void handleDone() throws DukeException {
        if (commandArr.length < 2) {
            throw new MissingTaskNumberException("Missing task number");
        }
        int taskNumber = Integer.parseInt(commandArr[1]);
        try {
            tasklist.get(taskNumber).setDone(true);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException("Task does not exist");
        }
        UI.done(taskNumber);
        tasklist.list();
    }

    public static void handleTodo() throws DukeException {
        if (commandArr.length < 2) {
            throw new MissingTaskNameException("Missing task name");
        }
        String remaining = command.substring(5);
        tasklist.add(new ToDo(remaining));
        UI.added("todo");
        UI.numberOfTasks(tasklist.size());
    }

    public static void handleDeadline() throws DukeException {
        if (commandArr.length < 2) {
            throw new MissingTaskNameException("Missing task name");
        }
        int byIndex = command.indexOf("/by");
        if (byIndex == -1) {
            throw new MissingDeadlineException("Missing deadline");
        }
        String deadlineName = command.substring(9, byIndex - 1);
        String deadlineByString = command.substring(byIndex + 4);
        LocalDate deadlineBy = LocalDate.parse(deadlineByString);
        tasklist.add(new Deadline(deadlineName, deadlineBy));
        UI.added("deadline");
        UI.numberOfTasks(tasklist.size());
    }

    public static void handleEvent() throws DukeException {
        if (commandArr.length < 2) {
            throw new MissingTaskNameException("Missing task name");
        }
        int atIndex = command.indexOf("/at");
        if (atIndex == -1) {
            throw new MissingEventTimeException("Missing event time");
        }
        String eventName = command.substring(6, atIndex - 1);
        String eventAtString = command.substring(atIndex + 4);
        LocalDate eventAt = LocalDate.parse(eventAtString);
        tasklist.add(new Event(eventName, eventAt));
        UI.added("event");
        UI.numberOfTasks(tasklist.size());
    }

    public static void handleDelete() throws DukeException {
        if (commandArr.length < 2) {
            throw new MissingTaskNumberException("Missing task number");
        }
        int deleteTaskNumber = Integer.parseInt(commandArr[1]);
        try {
            tasklist.remove(deleteTaskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException("Task does not exist");
        }
        UI.delete(deleteTaskNumber);
        UI.numberOfTasks(tasklist.size());
        tasklist.list();
    }
}
