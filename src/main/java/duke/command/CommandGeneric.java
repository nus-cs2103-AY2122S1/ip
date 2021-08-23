package duke.command;

import duke.task.TaskList;

public class CommandGeneric extends DukeCommand{
    @Override
    public void execute(TaskList tl) {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
