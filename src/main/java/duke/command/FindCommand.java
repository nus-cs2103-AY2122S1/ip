package duke.command;

import duke.exception.EmptyDescriptionException;
import duke.task.Task;
import duke.util.ExceptionChecker;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * A class that handles command of finding a task by searching for a keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a FindCommand that handles command of finding a task by searching for a keyword.
     */
    public FindCommand(String keyword) throws EmptyDescriptionException {
        ExceptionChecker.checkEmptyDescription("find", keyword);
        this.keyword = keyword;
    }

    // Appends matched result to response.
    private void appendMatched(StringBuilder response, String task, int index) {
        String matchedResult = String.format(" %d.%s\n", index, task);
        if (task.contains(keyword)) {
            response.append(matchedResult);
        }
    }

    /**
     * Returns the response after finding a task by searching for a keyword.
     *
     * @param tasks The list that stores all the tasks to be added/deleted.
     * @param ui The ui that deals with interactions with the user.
     * @param storage The storage that deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder response = new StringBuilder("Oh? You're approaching me?\n"
                + "Here are the matching tasks in your list:\n");
        int index = 1;
        for (Task task : tasks.getTasks()) {
            String taskString = task.toString();
            appendMatched(response, taskString, index++);
        }

        return response.toString();
    }

    /**
     * Returns the boolean false since it is not a command that exits the program.
     *
     * @return The boolean false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
