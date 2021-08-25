package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.ArrayList;
import java.util.List;

/** A class that handles command of finding a task by searching for a keyword. */
public class FindCommand extends Command {

    private String keyword;

    /**
     * A constructor for class FindCommand.
     *
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Find a task by searching for a keyword.
     *
     * @param tasks The list that stores all the tasks to be added/deleted.
     * @param ui The ui that deals with interactions with the user.
     * @param storage The storage that deals with loading tasks from the file and saving tasks in the file.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("\tHere are the matching tasks in your list:");
        int index = 1;

        for (Task task : tasks.getTasks()) {
            String taskString = task.toString();
            if (taskString.contains(this.keyword)) {
                String matchedResult = String.format("\t %d.%s", index++, taskString);
                System.out.println(matchedResult);
            }
        }
    }

    /**
     * Return a boolean value of whether it is a command that exit the program.
     *
     * @return The boolean value of whether it is a command that exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
