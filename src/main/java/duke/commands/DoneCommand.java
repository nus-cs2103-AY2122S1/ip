package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidDescriptionException;
import duke.exceptions.NoDescriptionException;
import duke.task.Task;

import java.io.IOException;

public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_SUCCESS = "Noted. I've marked this as done: %1$s";
    int index;

    public DoneCommand(String fullCommand) throws NoDescriptionException, InvalidDescriptionException {
        if (fullCommand.equals("done")) {
            throw new NoDescriptionException("Please specify the task number.");
        }

        String[] splitCommand = fullCommand.split(" ", 2);
        String desc = splitCommand[1];

        try {
            index = Integer.parseInt(desc);
        } catch (NumberFormatException e) {
            throw new InvalidDescriptionException("Please append a task number after 'done'.");
        }
    }
    @Override
    public CommandResult execute() {
        try {
            Task taskToMarkDone = tasks.markAsDone(index);
            storage.rewriteFile(tasks);
            return new CommandResult(String.format(MESSAGE_SUCCESS, taskToMarkDone));
        } catch (DukeException | IOException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
