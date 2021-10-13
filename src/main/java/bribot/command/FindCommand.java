package bribot.command;

import java.util.ArrayList;

import bribot.exception.DukeException;
import bribot.storage.Storage;
import bribot.task.Task;
import bribot.task.TaskList;
import bribot.ui.Ui;

/**
 * Represents a command given to find tasks that match a given string.
 */

public class FindCommand extends Command {
    private String input;

    /**
     * Creates the FindCommand given the String input.
     * @param input the given input string.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Returns false since FindCommand is not an ExitCommand.
     * @return flase since FindCommand is not an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command causing the tasks to find tasks that description matches the given string and gets the ui
     * to print them.
     * @param tasks the TaskList of the program.
     * @param ui the user interface of the program.
     * @param storage the storage of the program.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> matchingTasks = tasks.find(input);
        if (matchingTasks.size() == 0) {
            return ui.printMessage("Oh no looks like there are no tasks\nthat match what you searched for");
        } else {
            StringBuilder response = new StringBuilder();
            response.append(ui.printStartList());
            for (int i = 0; i < matchingTasks.size(); i++) {
                response.append(ui.printTask(matchingTasks.get(i), i + 1));
            }
            return response.toString();
        }
    }
}
