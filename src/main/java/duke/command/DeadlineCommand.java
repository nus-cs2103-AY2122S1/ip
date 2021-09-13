package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.TaskList;

/**
 * Represents the command when the user wants to add a deadline into the task list.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    /** The description of the event to be added. */
    private String description;
    /** The date of the event to be added. */
    private String date;
    /** The time of the event to be added. */
    private String time;

    /**
     * Constructs a DeadlineCommand object.
     *
     * @param description The description of the event to be added.
     * @param date The date of the event to be added.
     * @param time The time of the event to be added.
     */
    public DeadlineCommand(String description, String date, String time) {
        this.description = description;
        this.date = date;
        this.time = time;
    }

    /**
     * Executes the command to add an event to the task list.
     *
     * @param tasks The user's task list.
     * @param storage Storage object used to save the task list to the data file.
     * @return A message informing the user that the deadline has been added and the
     * total number of tasks he has now.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Deadline newDeadline = new Deadline(description, date, time);
        tasks.addTask(newDeadline);
        storage.save(tasks);
        return Ui.getAddedMessage(newDeadline, tasks.size());
    }
}
