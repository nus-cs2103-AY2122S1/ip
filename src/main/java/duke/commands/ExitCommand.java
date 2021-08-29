package duke.commands;

import duke.TaskList;
import duke.Ui;

public class ExitCommand implements Command {

    @Override
    public String execute(TaskList taskList, Ui ui) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
