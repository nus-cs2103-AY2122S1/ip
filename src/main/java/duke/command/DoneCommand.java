package duke.command;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

public class DoneCommand extends Command {
    final static String cmd = "done";
    final static String usage = "marks specified task as done";
    final static String format =  "done {task number}";

    private int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public DoneCommand() {}

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(this.taskNum);
        task.markAsDone();
        storage.updateData(tasks);
        return ui.showTaskDone(task);
    }

    public static String getCmd() {
        return cmd;
    }

    public static String getUsage() {
        return usage;
    }

    public static String getFormat() {
        return format;
    }
}
