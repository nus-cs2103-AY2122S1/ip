package duke.command;

import duke.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

import java.time.LocalDate;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String USAGE_TEXT = "Usage: event [description] /at [date in YYYY-MM-DD format]";

    private String description;
    private LocalDate date;

    public EventCommand(String desc, LocalDate date) {
        description = desc;
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Add new event
        Task task = new Event(description, date);
        taskList.addTask(task);
        ui.showTasksReply(true, task.toString(), taskList.size());
    }
}
