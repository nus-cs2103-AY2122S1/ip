package duke.logic.command;

import duke.logic.tasks.TaskList;

public class ByeCommand extends Command {
    @Override
    public String executeCommand(TaskList taskList) {
        return "Bye. Hope to see you again soon!";
    }
}
