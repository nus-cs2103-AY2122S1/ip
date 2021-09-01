package duke.commands;

import duke.TaskList;

public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList) {
        String output = String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                taskList.deleteFromList(index), taskList.taskCount());
        return output;
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
