package duke.commands;

import duke.TaskList;
import duke.Ui;

public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) {
        String output = String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                taskList.deleteFromList(index), taskList.taskCount());
        return output;
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
