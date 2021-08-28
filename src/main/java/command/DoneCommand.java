package command;

import exceptions.DukeException;
import helpers.helpers;
import storage.Storage;
import task.Task;
import task.TaskList;
import parser.Parser;
import ui.Ui;

public class DoneCommand extends Command {
    private final String command;
    private final String delimiter;

    private final String MESSAGE_USAGE = String.format("Usage: %s <number>", Parser.COMMAND_DONE);
    private final String MESSAGE_SUCCESS = "This task is marked as done:\n%s";

    public DoneCommand(String command, String delimiter) {
        this.command = command;
        this.delimiter = delimiter;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] commands = command.split(delimiter, 2);
        if (commands.length < 2) {
            throw new DukeException(MESSAGE_USAGE);
        }

        // Check if second input is an integer
        boolean isInteger = helpers.isInteger(commands[1]);
        if (!isInteger) {
            throw new DukeException(MESSAGE_USAGE);
        }

        // Mark the task in tasks list as done
        int taskIndex = Integer.parseInt(commands[1]);
        Task task;
        try {
            task = tasks.getTask(taskIndex);
            task.markAsDone();
        } catch (DukeException e) {
            String errorMessage = e.getMessage() + "\n" + MESSAGE_USAGE;
            throw new DukeException(errorMessage);
        }

        // Tries to write to storage first
        // If failed, mark the task as not done.
        try {
            storage.writeTasksToFile(tasks);
        } catch (DukeException e) {
            task.markAsNotDone();
            String errorMessage = e.getMessage() + "\n" + MESSAGE_USAGE;
            throw new DukeException(errorMessage);
        }

        ui.print(String.format(MESSAGE_SUCCESS, task.toString()));
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
