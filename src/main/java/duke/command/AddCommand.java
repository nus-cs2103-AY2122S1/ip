package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.util.DataManager;
import duke.util.TaskList;

/**
 * This class encapsulates the command dealing with adding tasks to the list.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class AddCommand extends Command {
    private static final String ERROR_TODO_EMPTY_DESCRIPTION =
            "Todo command has to be followed by a task description!";
    private static final String ERROR_DEADLINE_EMPTY_DESCRIPTION =
            "Deadline command has to be followed by a task description!";
    private static final String ERROR_DEADLINE_MISSING_DATE =
            "Please specify the deadline of this task using '/by <date>'.";
    private static final String ERROR_MULTIPLE_DATES =
            "There should only be one date/time specified!";
    private static final String ERROR_EVENT_MISSING_DESCRIPTION =
            "Event command has to be followed by a task description!";
    private static final String ERROR_EVENT_MISSING_DATE =
            "Please specify the date of this event using '/at <date>'.";

    private final TaskList list;
    private final DataManager dataManager;
    private final String taskType;

    /**
     * Constructs an AddCommand containing the current state of the todolist, datamanager and task
     * type.
     *
     * @param list ToDoList currently used by Duke.
     * @param dataManager DataManager currently used by Duke.
     * @param taskType The type of add command used.
     */
    public AddCommand(TaskList list, DataManager dataManager, String taskType) {
        this.list = list;
        this.dataManager = dataManager;

        assert taskType.matches("todo|event|deadline"); // pre-condition
        this.taskType = taskType;
    }

    @Override
    public String getResponse(String input) {
        switch (taskType) {
        case "event":
            return handleEvent(input);
        case "todo":
            return handleTodo(input);
        case "deadline":
            return handleDeadline(input);
        default:
            return "";
        }
    }

    /**
     * Handles ToDos task creation.
     *
     * @param input Raw user's input.
     * @return response by Duke when task is completed.
     */
    private String handleTodo(String input) {
        String[] extracted = input.split(" ", 2);

        // Check whether description is entered
        if (extracted.length < 2) {
            return ERROR_TODO_EMPTY_DESCRIPTION;
        }

        // Handles multiple todos in one command.
        if (input.split(", ").length >= 2) {
            return handleMultipleTodos(extracted[1]);
        }

        ToDo task = new ToDo(extracted[1]);
        try {
            dataManager.writeToFile(task);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return list.addToList(task);
    }

    private String handleMultipleTodos(String s) {
        String[] inputTasks = s.split(", ");
        Task[] tasks = new Task[inputTasks.length];
        try {
            for (int i = 0, inputTasksLength = inputTasks.length; i < inputTasksLength; i++) {
                String inputTask = inputTasks[i];
                ToDo task = new ToDo(inputTask);
                tasks[i] = task;
                dataManager.writeToFile(task);
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
        return list.addToList(tasks);
    }

    /**
     * Handles Deadline task creation.
     *
     * @param input Raw user's input.
     * @return response by Duke when task is completed.
     */
    private String handleDeadline(String input) {
        // Check whether description is entered
        if (input.split(" ").length < 2) {
            return ERROR_DEADLINE_EMPTY_DESCRIPTION;
        }

        String[] extracted = input.split(" ", 2)[1].split(" /by ");

        // Check whether deadline is specified correctly
        if (extracted.length < 2) {
            return ERROR_DEADLINE_MISSING_DATE;
        } else if (extracted.length > 2) {
            return ERROR_MULTIPLE_DATES;
        }

        String description = extracted[0];
        String deadline = extracted[1];

        Deadline task = new Deadline(description, deadline);
        try {
            dataManager.writeToFile(task);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return list.addToList(task);
    }

    /**
     * Handler for Event task creation.
     *
     * @param input Raw user's input.
     * @return response by Duke when task is completed.
     */
    private String handleEvent(String input) {
        // Check whether description is entered
        if (input.split(" ").length < 2) {
            return ERROR_EVENT_MISSING_DESCRIPTION;
        }

        String[] extracted = input.split(" ", 2)[1].split(" /at ");

        // Check whether deadline is specified correctly
        if (extracted.length < 2) {
            return ERROR_EVENT_MISSING_DATE;
        } else if (extracted.length > 2) {
            return ERROR_MULTIPLE_DATES;
        }

        String description = extracted[0];
        String dateTime = extracted[1];

        Event task = new Event(description, dateTime);
        try {
            dataManager.writeToFile(task);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return list.addToList(task);
    }
}
