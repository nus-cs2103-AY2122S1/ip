package duke.command;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    final static String cmd = "delete";
    final static String usage = "deletes specified task";
    final static String format =  "delete {task number}";

    private int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public DeleteCommand() {}

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(taskNum);
        tasks.deleteTask(task);
        storage.updateData(tasks);
        return ui.showTaskDeleted(task, tasks.getSize());
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
