package duke.commands;

import duke.utils.TaskList;

public class List extends Command {
    @Override
    public TaskList execute (TaskList taskList) {
        return taskList;
    }
}
