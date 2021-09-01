package duke.commands;

import duke.TaskList;

public class DoneCommand implements Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList) {
        String output = "Nice! I've marked this task as done:\n" + taskList.taskDone(index);
        return output;
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
