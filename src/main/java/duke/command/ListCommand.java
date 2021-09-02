package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates the list command.
 */
public class ListCommand implements Command {

    /**
     * List all tasks in the given task list.
     *
     * @param tasks User's list of tasks.
     * @param ui Duke's UI.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        if (tasks.size() == 0) {
            System.out.println("There are no tasks in your list yet.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; i++) {
            System.out.println(i + "." + tasks.get(i - 1));
        }
    }

    /**
     * Indicates if the command is an exit command.
     * @return If the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
