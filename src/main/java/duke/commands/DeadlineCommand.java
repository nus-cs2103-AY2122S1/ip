package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.MissingTimeCommandException;
import duke.exceptions.NoDescriptionException;
import duke.task.Task;
import duke.task.Deadline;

import java.io.IOException;

/**
 * Represents a To Do Command.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_SUCCESS = "Task successfully added: %1$s";
    private Task taskToAdd;



    public DeadlineCommand(String fullCommand) throws NoDescriptionException, MissingTimeCommandException {
        if (fullCommand.equals("deadline")) {
            throw new NoDescriptionException("The description of a deadline cannot be empty.");
        }
        if (!fullCommand.contains("/by")) {
            throw new MissingTimeCommandException("Missing Time Command: add '/by' in the command.");
        }
        String[] splitCommand = fullCommand.split(" ", 2);
        String[] body = splitCommand[1].split(" /by ", 2);
        String desc = body[0];
        String date = body[1];
        taskToAdd = new Deadline(desc, date);
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
