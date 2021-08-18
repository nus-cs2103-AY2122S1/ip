package commands;

import core.TaskList;

public class DoneCommand extends Command {
    private int indexOfCompleted;

    public DoneCommand(int indexOfCompleted) {
        this.indexOfCompleted = indexOfCompleted;
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.markAsDone(indexOfCompleted);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
