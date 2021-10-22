package duke.command;

import java.util.Map;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidCommandException;
import duke.task.TaskList;

@FunctionalInterface
interface DukeCommandAction {
    /**
     * Processes the given command (a line). Returns true if more commands are to be listened to.
     *
     * @param taskList  The list of tasks.
     * @param ui        The {@link duke.Duke} object which the command uses to execute its commands.
     * @param storage   The storage object representing the file in which the tasks are stored.
     * @param arg       The positional argument to the command.
     * @param namedArgs The named arguments to the command.
     */
    void apply(TaskList taskList, Ui ui, Storage storage,
               String arg, Map<String, String> namedArgs) throws InvalidCommandException;
}
