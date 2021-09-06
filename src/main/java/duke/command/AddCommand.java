package duke.command;

import java.time.format.DateTimeParseException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;


/**
 * The type Add command that adds a user-specified task to a given list of tasks.
 */
public class AddCommand extends Command {

    /** Enum that specifies the type of task being added. */
    private final AddCommandType addType;
    /** Task Description. */
    private final String description;
    /** List of tasks to run the command on. */
    private final TaskList tasks;
    /** Array of date time parameters for certain tasks. */
    private final String[] dateTimeArgs;


    /**
     * Instantiates a new Add command.
     *
     * @param addType      the type of task added.
     * @param description  the description for task to be added.
     * @param tasks        the list of tasks to add the new task to.
     * @param dateTimeArgs the datetime parameters for tasks.
     */

    public AddCommand(AddCommandType addType, String description, TaskList tasks, String... dateTimeArgs) {
        assert description != null : "Task Description cannot be null.";
        assert tasks != null : "TaskList cannot be null.";
        assert addType != null : "Command type cannot be null.";
        this.addType = addType;
        this.description = description;
        this.tasks = tasks;
        this.dateTimeArgs = dateTimeArgs;
    }
    @Override
    public String execute() {
        Task newTask = null;

        try {
            switch (this.addType) {
            case todo:
                newTask = createTodo();
                break;
            case event:
                newTask = createEvent();
                break;
            case deadline:
                newTask = createDeadline();
                break;
            default:
                break;
            }
        } catch (DateTimeParseException e) {
            return "☹ OOPS!!! Please enter an appropriate date (and optionally, 24-hour time)\n"
                                + "Format: YYYY-MM-DD HH:MM";
        }

        this.tasks.add(newTask);
        return "Got it. I've added this task:\n  "
                + newTask.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Instantiates a new instance of Todo based on user inputted parameters.
     *
     * @return a new instance of Todo task.
     */
    private Task createTodo() {
        return new Todo(description);
    }

    /**
     * Instantiates a new instance of Event based on user inputted parameters.
     *
     * @return a new instance of Event task.
     * @throws DateTimeParseException when task creation fails to parse date/time.
     */
    private Task createEvent() throws DateTimeParseException {
        // For events with only date information.
        if (dateTimeArgs.length == 1) {
            return new Event(description, dateTimeArgs[0]);
        }
        // For events with both date and time information./
        return new Event(description, dateTimeArgs[0], dateTimeArgs[1]);
    }

    /**
     * Instantiates a new instance of Deadline based on user inputted parameters.
     *
     * @return a new instance of Deadline task.
     * @throws DateTimeParseException when task creation fails to parse date/time.
     */
    private Task createDeadline() throws DateTimeParseException {
        // For events with only date information.
        if (dateTimeArgs.length == 1) {
            return new Deadline(description, dateTimeArgs[0]);
        }
        // For events with both date and time information.
        return new Deadline(description, dateTimeArgs[0], dateTimeArgs[1]);
    }

}
