package duke.command;

import duke.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

import duke.Storage;
import duke.Ui;

import java.time.LocalDate;

/**
 * Represents a command to add an event
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    public static final String USAGE_TEXT = "Usage: event [description] /at [date in YYYY-MM-DD format]";

    /** Description of event */
    private String description;
    /** Date of event */
    private LocalDate date;

    /**
     * EventCommand constructor.
     *
     * @param desc Description of event.
     * @param date Date of event.
     */
    public EventCommand(String desc, LocalDate date) {
        description = desc;
        this.date = date;
    }

    /**
     * Adds new Event task to given TaskList and displays the relevant message.
     *
     * @param taskList List of tasks.
     * @param ui User interface.
     * @param storage Storage of tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = new Event(description, date);
        taskList.addTask(task);
        return ui.showTasksReply(true, task.toString(), taskList.size());
    }
}
