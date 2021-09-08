package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidDescriptionException;
import duke.exceptions.NoDescriptionException;
import duke.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_SUCCESS = "Noted. I've marked this as done: %1$s";
    int index;

    public DeleteCommand(String fullCommand) throws NoDescriptionException, InvalidDescriptionException {
        if (fullCommand.equals("delete")) {
            throw new NoDescriptionException("Please specify a task to delete.");
        }

        String[] splitCommand = fullCommand.split(" ", 2);
        String desc = splitCommand[1];

        try {
            index = Integer.parseInt(desc);
        } catch (NumberFormatException e) {
            throw new InvalidDescriptionException("Please append a task number after 'delete'.");
        }

        assert index > 0 : "Index provided is negative";
    }

    public CommandResult execute() {
        try {
            Task taskToDelete = tasks.deleteTask(index);
            storage.rewriteFile(tasks);
            return new CommandResult(String.format(MESSAGE_SUCCESS, taskToDelete));
        } catch (DukeException | IOException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
