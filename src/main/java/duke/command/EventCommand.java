package duke.command;

import java.time.LocalDateTime;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.task.Event;
import duke.ui.Ui;
public class EventCommand extends Command {
    static final String CMD = "event";
    static final String USAGE = "add tasks that need that starts at a specific time";
    static final String FORMAT = "event {task name} /at {yyyy-MM-dd HH:mm}";

    private String name;
    private LocalDateTime at;

    /**
     * Constructs an EventCommand object
     *
     * @param name name of task
     * @param at date and time for task to commence
     */
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
        return CMD;
    }

    public static String getUsage() {
        return USAGE;
    }

    public static String getFormat() {
        return FORMAT;
    }
}
