package duke.command;

import duke.exception.DukeException;
import duke.task.Todo;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the TodoCommand in the Duke program.
 */
public class TodoCommand extends Command {
    private final Todo todo;

    /**
     * Constructs a TodoCommand to create a Todo task with the given description.
     *
     * @param description Description of the Todo task.
     */
    public TodoCommand(String description) {
        todo = new Todo(description);
    }

    /**
     * Returns the response after creating a Todo task.
     *
     * @param tasks   Tasks of the Duke program.
     * @param ui      Ui of the Duke program.
     * @param storage Storage of the Duke program.
     * @throws DukeException If changes cannot be saved to storage.
     */
    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(todo);

        String response = "Got it. I've added this task:\n"
                + " " + todo + "\n"
                + "Now you have "
                + tasks.getSize() + (tasks.getSize() > 1 ? " tasks" : " task")
                + " in the list.";

        storage.save(tasks.getTaskList());

        return response;
    }

    /**
     * Returns false as this command is not the ExitCommand.
     *
     * @return false as this command is not the ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
