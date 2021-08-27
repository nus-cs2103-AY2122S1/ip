package duke.command;

import duke.exception.DukeException;
import duke.exception.MissingDateTimeException;
import duke.exception.MissingDescriptionException;
import duke.exception.MultipleDateTimeException;
import duke.task.Deadline;
import duke.task.Event;
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
    private final String input;

    public AddCommand(ToDoList list, DataManager dataManager, String taskType, String input) {
        this.list = list;
        this.dataManager = dataManager;
        this.taskType = taskType;
        this.input = input;
    }

    /** Detects and handles different add commands appropriately. */
    @Override
    public void execute() throws DukeException {
        switch (taskType) {
        case "event":
            handleEvent(input);
            break;
        case "todo":
            handleTodo(input);
            break;
        case "deadline":
            handleDeadline(input);
            break;
        default:
            break;
        }
    }

    /**
     * Handles ToDos task creation.
     *
     * @param input Raw user's input.
     * @throws MissingDescriptionException if no description is entered after todo command.
     */
    private void handleTodo(String input) throws DukeException {
        String[] extracted = input.split(" ", 2);

        // Check whether description is entered
        if (extracted.length < 2) {
            throw new MissingDescriptionException();
        }

        ToDo task = new ToDo(extracted[1]);
        list.addToList(task);
        dataManager.writeToFile(task);
    }

    /**
     * Handles Deadline task creation.
     *
     * @param input Raw user's input.
     * @throws MissingDescriptionException if no description is entered after deadline command.
     * @throws MissingDateTimeException if no date/time is entered following a deadline command.
     * @throws MultipleDateTimeException if multiple date/time is detected in user input.
     */
    private void handleDeadline(String input) throws DukeException {
        // Check whether description is entered
        if (input.split(" ").length < 2) {
            throw new MissingDescriptionException();
        }

        String[] extracted = input.split(" ", 2)[1].split(" /by ");

        // Check whether deadline is specified correctly
        if (extracted.length < 2) {
            throw new MissingDateTimeException("'/by'");
        } else if (extracted.length > 2) {
            throw new MultipleDateTimeException();
        }

        String description = extracted[0];
        String deadline = extracted[1];

        Deadline task = new Deadline(description, deadline);
        list.addToList(task);
        dataManager.writeToFile(task);
    }

    /**
     * Handler for Event task creation.
     *
     * @param input Raw user's input.
     * @throws MissingDescriptionException if no description is entered after event command.
     * @throws MissingDateTimeException if no date/time is entered following an event command.
     * @throws MultipleDateTimeException if multiple date/time is detected in user input.
     */
    private void handleEvent(String input) throws DukeException {
        // Check whether description is entered
        if (input.split(" ").length < 2) {
            throw new MissingDescriptionException();
        }

        String[] extracted = input.split(" ", 2)[1].split(" /at ");

        // Check whether deadline is specified correctly
        if (extracted.length < 2) {
            throw new MissingDateTimeException("'/at'");
        } else if (extracted.length > 2) {
            throw new MultipleDateTimeException();
        }

        String description = extracted[0];
        String dateTime = extracted[1];

        Event task = new Event(description, dateTime);
        list.addToList(task);
        dataManager.writeToFile(task);
    }
}
