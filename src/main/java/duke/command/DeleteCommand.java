package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class DeleteCommand extends Command {

    private final int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTasks().get(this.taskNum - 1);
        tasks.delete(this.taskNum - 1);
        storage.save(tasks);
        System.out.println("\tNoted. I've %s this task:");
        System.out.println("\t " + task);
        System.out.println("\tNow you have " + tasks.getTaskNum() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
