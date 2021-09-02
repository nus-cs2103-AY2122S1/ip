package duke.command;

import duke.TaskList;
import duke.Ui;

public class ListCommand implements Command {

    @Override
    public void execute(TaskList tasks, Ui ui) {
        if (tasks.getSize() == 0) {
            System.out.println("There are no tasks in your list yet.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < tasks.getSize() + 1; i++) {
            System.out.println(i + "." + tasks.get(i - 1));
        }
    }

    public boolean isExit() {
        return false;
    }
}
