package duke.command;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

public class FindCommand extends Command {
    private final String COMMAND;
    private final String DELIMITER;

    private final String MESSAGE_USAGE = String.format("Usage: %s <number>", Parser.COMMAND_FIND);
    private final String MESSAGE_SUCCESS = "Here are the matching tasks in your list:\n%s";

    public FindCommand(String command, String delimiter) {
        this.COMMAND = command;
        this.DELIMITER = delimiter;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String[] commands = COMMAND.split(DELIMITER, 2);
        if (commands.length < 2) {
            throw new DukeException(MESSAGE_USAGE);
        }

        String matchingTasks = tasks.findTasksContainingString(commands[1]).toString();
        return String.format(MESSAGE_SUCCESS, matchingTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
