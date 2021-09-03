package duke.commands;

import java.util.ArrayList;

import duke.exceptions.AuguryException;
import duke.exceptions.InvalidActionException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.util.StringCleaner;

/**
 * The {@code MarkDoneCommand} class extends from {@code Command}, which
 * marks specified {@code Task}s from the {@code TaskList} as done.
 */
public class MarkDoneCommand extends Command {

    private final String[] args;

    /**
     * Initializes a {@code MarkDoneCommand} with user-supplied {@code String[] arguments}.
     *
     * @param arguments User-supplied arguments (expected format: first index of array contains a
     *                  {@code String} of comma-separated integers ["2, 3, 5"])
     */
    public MarkDoneCommand(String[] arguments) {
        this.args = arguments;
    }

    /**
     * Marks as done the {@code Task}s at indices provided as arguments.
     *
     * @param tasks {@code TaskList} that {@code MarkDoneCommand} operates on.
     * @param storage {@code Storage} instance that the {@code MarkDoneCommand} writes to.
     * @return {@code String} containing confirmation of {@code Task}s marked as done.
     * @throws InvalidActionException if {@code Task} that does not exist is to be marked.
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

        String result = tasks.markAsDoneAndAnnounce(listOfTasks);
        storage.saveTaskListToStorage(tasks);
        return result;
    }
}
