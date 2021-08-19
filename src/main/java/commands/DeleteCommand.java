package commands;

import core.TaskList;

public class DeleteCommand extends Command {
    private int indexToDelete;

    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.delete(indexToDelete);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
/*
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

 */