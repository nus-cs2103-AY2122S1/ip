package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

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
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
        int index = 1;

        for (Task task : tasks.getTasks()) {
            String taskString = task.toString();
            if (taskString.contains(this.keyword)) {
                String matchedResult = String.format("\t %d.%s\n", index++, taskString);
                response.append(matchedResult);
            }
        }

        return response.toString();
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
