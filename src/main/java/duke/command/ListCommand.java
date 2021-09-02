package duke.command;

import duke.TaskList;
import duke.Ui;

public class ListCommand implements Command {

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
