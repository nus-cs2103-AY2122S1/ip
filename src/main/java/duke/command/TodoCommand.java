package duke.command;

import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Todo;

/**
 * Represents a todo command.
 */
public class TodoCommand extends Command {

    private Todo todo;

    public TodoCommand(String input) throws DukeException {
        String[] parsed = input.split(" ", 2);
        assert parsed.length == 2 : "Invalid command entered.";
        if (parsed.length != 2) {
            throw new DukeException("Please enter a task after the command.");
        }
        String task = parsed[1];
        this.todo = new Todo(task);
    }

    /**
     * Returns the todo object
     * @return The todo object
     */
    public Todo getTodo() {
        return this.todo;
    }

    /**
     * Returns the result of the execution of the todo command.
     * @param tasks List of tasks the user has added.
     * @return Result of the execution of the todo command.
     */
    @Override
    public String execute(TaskList tasks) {
        return tasks.addTask(this.todo);
    }
}
