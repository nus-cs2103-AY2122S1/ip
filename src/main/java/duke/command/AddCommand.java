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
        super(false);
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
        if (type.equals("todo")) {
            tasks.addTodoTask(description);
        } else if (type.equals("deadline")) {
            tasks.addDeadlineTask(description);
        } else {
            tasks.addEventtask(description);
        }

        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new DukeException("OOPS!! something went wrong while trying to update tasks");
        }

        return ui.addResposne(tasks.getTask(tasks.getSize() - 1), tasks.getSize());
    }
}
