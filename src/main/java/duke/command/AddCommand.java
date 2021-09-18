package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.UI;
import duke.error.DukeException;
import duke.task.TaskList;

/**
 * Represents the command of adding a task to the list.
 */
public class AddCommand extends Command {
    private final String type;
    private final String description;

    /**
     * Constructs an AddCommand object.
     *
     * @param type Task type.
     * @param description Task description.
     */
    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }

    /**
     * Adds a new task to the list of tasks.
     *
     * @param tasks List of tasks.
     * @param ui UI object.
     * @param storage Storage object.
     * @return The execution result.
     * @throws DukeException If something goes wrong while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("OOPS!! the description of add cannot be empty.");
        }

        switch (type) {
        case "todo":
            tasks.addTodoTask(description);
            break;
        case "deadline":
            tasks.addDeadlineTask(description);
            break;
        case "event":
            tasks.addEventtask(description);
            break;
        default:
            throw new DukeException("OOPS!! invalid task type");
        }

        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new DukeException("OOPS!! something went wrong while trying to update tasks");
        }

        return ui.addResposne(tasks.getTask(tasks.getSize() - 1), tasks.getSize());
    }
}
