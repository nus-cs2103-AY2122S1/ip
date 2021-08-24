package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class DoneCommand extends Command{

    private final int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTasks().get(this.taskNum - 1);
        task.markAsDone();
        storage.save(tasks);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t " + task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
