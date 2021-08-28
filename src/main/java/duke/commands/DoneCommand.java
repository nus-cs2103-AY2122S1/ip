package duke.commands;

import duke.TaskList;
import duke.Ui;

public class DoneCommand implements Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        String output = "Nice! I've marked this task as done:\n" + taskList.taskDone(index);
        ui.stringWithDivider(output);
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
