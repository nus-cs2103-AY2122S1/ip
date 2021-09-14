package duke.command;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

import duke.Ui;
import duke.Storage;

import java.time.LocalDate;

/**
 * Represents a command to add a task with deadline
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String USAGE_TEXT = "Usage: deadline [description] /by [date in YYYY-MM-DD format]";

    /** Description of task with deadline */
    private String description;
    /** Deadline of task */
    private LocalDate date;

    /**
     * DeadlineCommand constructor.
     *
     * @param desc Description of task with deadline.
     * @param date Deadline of task.
     */
    public DeadlineCommand(String desc, LocalDate date) {
        description = desc;
        this.date = date;
    }

    /**
     * Adds new Deadline task to given TaskList and displays the relevant message.
     *
     * @param taskList List of tasks.
     * @param ui User interface.
     * @param storage Storage of tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = new Deadline(description, date);
        taskList.addTask(task);
        return ui.showTasksReply(true, task.toString(), taskList.size());
    }
}
