package duke.command;

import duke.TaskList;
import duke.Ui;

public class ListCommand implements Command {

    @Override
    public String execute(TaskList tasks, Ui ui) {
        if (tasks.getSize() == 0) {
            return "There are no tasks in your list yet.";
        }
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 1; i < tasks.getSize() + 1; i++) {
            response.append(String.format("  %d. %s\n", i, tasks.get(i - 1)));
        }
        return response.toString();
    }

    /**
     * Indicates if the command is an exit command.
     *
     * @return If the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
