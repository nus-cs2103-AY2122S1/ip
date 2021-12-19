package duke.logic.command;

import duke.logic.tasks.TaskList;

public class ListCommand extends Command {
    @Override
    public String executeCommand(TaskList taskList) {
        return taskList.toString();
    }
}
