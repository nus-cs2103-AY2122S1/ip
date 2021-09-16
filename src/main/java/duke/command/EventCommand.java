package duke.command;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.task.Event;
import duke.ui.Ui;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    final static String cmd = "event";
    final static String usage = "add tasks that need that starts at a specific time";
    final static String format = "event {task name} /by {yyyy-MM-dd HH:mm}";

    private String name;
    private LocalDateTime at;

    public EventCommand(String name, LocalDateTime at) {
        this.name = name;
        this.at = at;
    };

    public EventCommand() {}

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(name, at);
        tasks.addTask(event);
        storage.updateData(tasks);
        return ui.showTaskAdded(event, tasks.getSize());
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
