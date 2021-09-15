package duke.command;

import duke.exceptions.DukeException;
import duke.helpers.Helpers;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    private final String COMMAND;
    private final String DELIMITER;

    private final String MESSAGE_USAGE = String.format("Usage: %s <number>", Parser.COMMAND_DONE);
    private final String MESSAGE_SUCCESS = "This task is marked as done:\n%s";

    /**
     * Constructor for Done Command.
     *
     * @param command String duke.command input by user.
     * @param delimiter String delimiter between the commands in the duke.command string input.
     */
    public DoneCommand(String command, String delimiter) {
        this.COMMAND = command;
        this.DELIMITER = delimiter;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String[] commands = COMMAND.split(DELIMITER, 2);
        if (commands.length < 2) {
            throw new DukeException(MESSAGE_USAGE);
        }

        // Check if second input is an integer
        boolean isInteger = Helpers.isInteger(commands[1]);
        if (!isInteger) {
            throw new DukeException(MESSAGE_USAGE);
        }

        // Mark the duke.task in tasks list as done
        int taskIndex = Integer.parseInt(commands[1]);
        Task task;
        try {
            task = tasks.getTask(taskIndex);
            task.markAsDone();
        } catch (DukeException e) {
            String errorMessage = e.getMessage() + "\n" + MESSAGE_USAGE;
            throw new DukeException(errorMessage);
        }

        // Tries to write to duke.storage first
        // If failed, mark the duke.task as not done.
        try {
            storage.writeTasksToFile(tasks);
        } catch (DukeException e) {
            task.markAsNotDone();
            String errorMessage = e.getMessage() + "\n" + MESSAGE_USAGE;
            throw new DukeException(errorMessage);
        }

        return String.format(MESSAGE_SUCCESS, task.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
