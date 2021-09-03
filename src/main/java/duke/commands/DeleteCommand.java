package duke.commands;

import java.util.ArrayList;

import duke.exceptions.AuguryException;
import duke.exceptions.InvalidActionException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.util.StringCleaner;

/**
 * The {@code DeleteCommand} class extends from {@code Command}, which
 * delete specified {@code Task}s from the {@code TaskList}.
 */
public class DeleteCommand extends Command {

    private final String[] args;

    /**
     * Initializes a {@code DeleteCommand} with user-supplied {@code String[] arguments}.
     *
     * @param arguments User-supplied arguments (expected format: first index of array contains a
     *                  {@code String} of comma-separated integers ["2, 3, 5"])
     */
    public DeleteCommand(String[] arguments) {
        this.args = arguments;
    }

    /**
     * Deletes {@code Task}s at indices provided as arguments.
     *
     * @param tasks {@code TaskList} that {@code DeleteCommand} operates on.
     * @param storage {@code Storage} instance that the {@code DeleteCommand} writes to.
     * @return {@code String} containing confirmation of {@code Task}s deleted.
     * @throws InvalidActionException if {@code Task} that does not exist is to be deleted.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws AuguryException {
        String args = this.args[0];
        ArrayList<Integer> listOfTasks = StringCleaner.toArrayListInteger(args);

        int taskListSize = tasks.getSize();
        for (Integer i : listOfTasks) {
            if (i > taskListSize) {
                throw new InvalidActionException("Task " + i + " does not exist.");
            }
        }

        String result = tasks.deleteTasksAndAnnounce(listOfTasks);
        storage.saveTaskListToStorage(tasks);
        return result;
    }
}
