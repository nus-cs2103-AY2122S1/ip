package duke.commands;

import duke.exceptions.NoDescriptionException;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;

/**
 * Represents a To Do Command.
 */
public class ToDoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_SUCCESS = "Task successfully added: %1$s";
    private Task taskToAdd;

    public ToDoCommand(String fullCommand) throws NoDescriptionException {
        if (fullCommand.equals("todo")) {
            throw new NoDescriptionException("The description of a todo cannot be empty.");
        }
        String[] splitCommand = fullCommand.split(" ", 2);
        String desc = splitCommand[1];
        taskToAdd = new ToDo(desc);
    }

    @Override
    public CommandResult execute() {
        tasks.add(taskToAdd);
        try {
            storage.rewriteFile(tasks);
            return new CommandResult(String.format(MESSAGE_SUCCESS, taskToAdd));
        } catch (IOException e) {
            return new CommandResult(e.getMessage());
        }
    }

    public Task getTaskToAdd() {
        return taskToAdd;
    }

}
