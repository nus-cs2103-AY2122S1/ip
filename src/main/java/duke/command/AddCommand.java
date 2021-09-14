package duke.command;

import duke.exception.DukeException;
import duke.exception.UnknownCommandDukeException;
import duke.exception.InvalidDeadlineDukeException;
import duke.exception.InvalidEventDukeException;
import duke.storage.Storage;
import duke.task.*;

/**
 * An AddCommand class that extends from the Command class.
 * @author KelvinSoo
 * @version A-MoreOOP
 */
public class AddCommand extends Command{

    private final String command;
    private final String description;
    private static final String SUCCESS_MESSAGE = "Got it. I've added this task:\n"
            + "  %s %s\nNow you have %d tasks in the list.";

    /**
     * A constructor to initialize an add command.
     * @param command The type of add command eg. todo, deadline, event.
     * @param description the description of the command.
     */
    public AddCommand(String command, String description) {
        this.command = command;
        this.description = description;
    }

    /**
     * A method to execute a command.
     * @param taskList The task list to execute the command on.
     * @param storage The place to store the session.
     * @throws DukeException error when adding a task.
     * @return The response.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        assert taskList != null : "task list should not be null.";
        Task task;
        switch (command) {
        case "todo":
            task = createTodo(description);
            break;
        case "deadline":
            task = createDeadline(description);
            break;
        case "event":
            task = createEvent(description);
            break;
        default:
            throw new UnknownCommandDukeException();
        }
        taskList.addTask(task);
        storage.save(taskList);
        return String.format(
                SUCCESS_MESSAGE, task.getStatusIcon(), task.getDescription(), taskList.size());
    }

    private boolean isValidParameter(String[] parameters) {
        if (parameters == null) {
            return false;
        } else {
            return parameters.length == 2;
        }
    }

    private Task createTodo(String description) {
        return new ToDo(description);
    }

    private Task createDeadline(String description) throws DukeException {
        String[] parameter = description.split(" /by ");
        if (isValidParameter(parameter)) {
            return new Deadline(parameter[0], parameter[1]);
        } else {
            throw new InvalidDeadlineDukeException();
        }
    }

    private Task createEvent(String description) throws DukeException {
        String[] parameter = description.split(" /at ");
        if (isValidParameter(parameter)) {
            return new Event(parameter[0], parameter[1]);
        } else {
            throw new InvalidEventDukeException();
        }
    }

    /**
     * A boolean to notate if this is an exit command.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
