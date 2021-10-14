package duke.command;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

public class DoneCommand extends Command {
    static final String CMD = "done";
    static final String USAGE = "marks specified task as done";
    static final String FORMAT = "done {task number}";

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
        return CMD;
    }

    public static String getUsage() {
        return USAGE;
    }

    public static String getFormat() {
        return FORMAT;
    }
}
