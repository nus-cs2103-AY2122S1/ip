package duke.command;

import java.util.ArrayList;
import java.util.Arrays;

import duke.exception.MessageEmptyException;
import duke.exception.TaskNotFoundException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the find command.
 */
public class FindCommand extends Command {

    /** Represents the find command keyword. */
    public static final String COMMAND = "find";

    /** The user input split by space bars. */
    private final String[] words;

    /**
     * Constructor of FindCommand.
     *
     * @param words user input split by space bars.
     */
    public FindCommand(String[] words) {
        this.words = words;
    }

    /**
     * Executes the command.
     *
     * @param tasks list of tasks.
     * @param ui ui to handle user interaction.
     * @param storage handles reading and writing of data file.
     * @return all tasks that matches the search query.
     * @throws MessageEmptyException if no search query is specified.
     * @throws TaskNotFoundException if tasks matching the query cannot be found.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MessageEmptyException, TaskNotFoundException {
        if (words.length == 1) {
            throw new MessageEmptyException();
        }

        ArrayList<String> isolateCommand = new ArrayList<>(Arrays.asList(words));

        // remove "find"
        isolateCommand.remove(0);

        String query = String.join(" ", isolateCommand);

        ArrayList<Task> matchedTasks = tasks.findTask(query);

        if (matchedTasks.size() == 0) {
            throw new TaskNotFoundException();
        }

        return Ui.printList(matchedTasks);
    }
}
