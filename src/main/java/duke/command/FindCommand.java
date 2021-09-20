package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the command to find tasks containing a particular keyword.
 *
 * @author Sherman Ng Wei Sheng
 */
public class FindCommand extends Command {
    private boolean isExit;
    private String keyword;

    /**
     * Constructor for the find command.
     *
     * @param keyword The keyword to be searched for.
     */
    public FindCommand(String keyword) {
        this.isExit = false;
        this.keyword = keyword;
    }

    /**
     * Returns true if the command is a program terminating command.
     *
     * @return True if it is a terminating command and false otherwise.
     */
    @Override
    public boolean isAExitCommand() {
        return isExit;
    }

    /**
     * Executes the task to find all tasks containing the keyword.
     *
     * @param list TaskList before execution of the command.
     * @param ui Ui object to log the execution of the command.
     * @param storage Storage object that references the path to store the updated list of tasks.
     * @return The string to be printed.
     * @throws DukeException If problem encountered during execution of command.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        int size = list.size();
        TaskList newList = new TaskList();
        for (int i = 0; i < size; i++) {
            Task current = list.get(i);
            String currentDescription = current.getDescription();
            if (currentDescription.contains(keyword)) {
                newList.add(current);
            }
        }

        int newListSize = newList.size();
        StringBuilder message = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < newListSize; i++) {
            int index = i + 1;
            Task content = newList.get(i);
            message.append("\n").append(index).append(".").append(content);
        }
        return ui.printAndReturnMessage(message.toString());
    }
}
