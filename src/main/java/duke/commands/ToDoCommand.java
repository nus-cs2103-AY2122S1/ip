package duke.commands;

import java.io.IOException;

import duke.exceptions.NoDescriptionException;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents a To Do Command.
 */
public class ToDoCommand extends Command {

    /** Unique command word */
    public static final String COMMAND_WORD = "todo";

    /** Successful execution message */
    public static final String MESSAGE_SUCCESS = "Task successfully added:\n%1$s";
    private Task taskToAdd;

    /**
     * Constructs a To Do Command.
     *
     * @param fullCommand User input.
     * @throws NoDescriptionException If there is no description.
     */
    public ToDoCommand(String fullCommand) throws NoDescriptionException {
        if (fullCommand.equals("todo")) {
            throw new NoDescriptionException("The description of a todo cannot be empty.");
        }
        String[] splitCommand = fullCommand.split(" ", 2);
        String desc = splitCommand[1];
        taskToAdd = new ToDo(desc);
    }

    /**
     * Executes the task and returns a CommandResult.
     *
     * @return CommandResult.
     */
    @Override
    public CommandResult execute() {
        tasks.add(taskToAdd);
        try {
            storage.appendToFile(taskToAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, taskToAdd));
        } catch (IOException e) {
            return new CommandResult(e.getMessage());
        }
    }

    /**
     * Returns the task to be added.
     *
     * @return Task.
     */
    public Task getTaskToAdd() {
        return taskToAdd;
    }

}
