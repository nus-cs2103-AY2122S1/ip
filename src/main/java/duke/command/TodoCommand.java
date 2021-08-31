package duke.command;

import duke.exception.NoActionException;
import duke.exception.SaveFileException;
import duke.task.Task;
import duke.task.Todo;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Reply;

/**
 * A command class encapsulating the logic that occurs when the user issues a 'todo' command.
 */
public class TodoCommand extends Command {
    private String action;

    /**
     * Constructor of the TodoCommand
     *
     * @param action String input of the tasks
     */
    public TodoCommand(String action) {
        super(false);
        this.action = action;
    }

    /**
     * Creates and adds a Todo to the tasks.
     *
     * @param tasks List of existing tasks
     * @param reply User interface current interacting with the user
     * @param storage Storage class handling the persistence of the tasks
     * @throws NoActionException if no action was provided
     * @throws SaveFileException if there is a save file related error
     */
    @Override
    public CommandResult execute(TaskList tasks, Reply reply, Storage storage) throws NoActionException, SaveFileException {
        if (action.length() == 0) {
            throw new NoActionException("Command 'todo' requires a task action");
        }
        Task newTask = new Todo(action);
        tasks.add(newTask);
        storage.save(tasks);
        return new CommandResult(Reply.showTaskAdded(newTask, tasks), true, super.isExit());
    }

    /**
     * Indicate if another object is 'equal' to this object.
     *
     * @param obj Reference object with which to compare to.
     * @return true if they are equal.
     *         false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TodoCommand) {
            TodoCommand otherTask = (TodoCommand) obj;
            return otherTask.action.equals(this.action);
        }
        return false;
    }
}
