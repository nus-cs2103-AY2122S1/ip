package duke.command;

import java.time.LocalDateTime;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.ui.Ui;


public class DeadlineCommand extends Command {
    static final String CMD = "deadline";
    static final String USAGE = "add tasks that need to be done before a specific date & time";
    static final String FORMAT = "deadline {task name} /by {yyyy-MM-dd HH:mm}";

    private String name;
    private LocalDateTime by;

    /**
     * Constructs a DeadlineCommand object
     *
     * @param name name of task
     * @param by date and time for task to be completed by
     */
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
        return CMD;
    }

    public static String getUsage() {
        return USAGE;
    }

    public static String getFormat() {
        return FORMAT;
    }
}
