package duke.command;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.ui.Ui;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    final static String cmd = "deadline";
    final static String usage = "add tasks that need to be done before a specific date & time";
    final static String format = "deadline {task name} /by {yyyy-MM-dd HH:mm}";

    private String name;
    private LocalDateTime by;

    public DeadlineCommand(String name, LocalDateTime by) {
        this.name = name;
        this.by = by;
    }

    public DeadlineCommand() {}

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(name, by);
        tasks.addTask(deadline);
        storage.updateData(tasks);
        return ui.showTaskAdded(deadline, tasks.getSize());
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
