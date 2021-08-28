package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.util.DataManager;
import duke.util.ToDoList;

/**
 * This class encapsulates the command dealing with adding tasks to the list.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class AddCommand extends Command {
    private final ToDoList list;
    private final DataManager dataManager;
    private final String taskType;

    public AddCommand(ToDoList list, DataManager dataManager, String taskType) {
        this.list = list;
        this.dataManager = dataManager;
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
            return "Todo command has to be followed by a task description!";
        }

        if (input.split(", ").length >= 2) {
            String[] inputTasks = extracted[1].split(", ");
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

        ToDo task = new ToDo(extracted[1]);
        try {
            dataManager.writeToFile(task);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return list.addToList(task);
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
            return "Deadline command has to be followed by a task description!";
        }

        String[] extracted = input.split(" ", 2)[1].split(" /by ");

        // Check whether deadline is specified correctly
        if (extracted.length < 2) {
            return "Please specify the deadline of this task using '/by <date>'.";
        } else if (extracted.length > 2) {
            return "There should only be one date/time specified!";
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
            return "Event command has to be followed by a task description!";
        }

        String[] extracted = input.split(" ", 2)[1].split(" /at ");

        // Check whether deadline is specified correctly
        if (extracted.length < 2) {
            return "Please specify the date of this event using '/at <date>'.";
        } else if (extracted.length > 2) {
            return "There should only be one date/time specified!";
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
