package duke.commands;

import duke.TaskList;

public class ExitCommand implements Command {

    @Override
    public String execute(TaskList taskList) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
