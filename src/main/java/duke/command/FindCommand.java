package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command given to find tasks that match a given string.
 */

public class FindCommand extends Command {
    String input;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> matchingTasks = tasks.find(input);
        if (matchingTasks.size() == 0) {
            ui.printLine();
            ui.printMessage("Oh no looks like there are no tasks\nthat match what you searched for");
            ui.printLine();
        } else {
            ui.printStartList();
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.printTask(matchingTasks.get(i), i + 1);
            }
            ui.printLine();
        }
    }
}
