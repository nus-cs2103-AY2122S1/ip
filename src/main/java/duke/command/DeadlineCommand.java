package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.TaskList;

import java.time.LocalDateTime;

/**
 * This class represents the Command when a user inputs "deadline" validly.
 */
public class DeadlineCommand extends Command {
    private String task;
    private LocalDateTime time;

    /**
     * Constructor for a deadline command.
     *
     * @param task detail of deadline task
     * @param time time of deadline
     */
    public DeadlineCommand(String task, LocalDateTime time) {
        this.task = task;
        this.time = time;
    }

    /**
     * Adds a new deadline to the task list, and saves the current task list.
     *
     * @param tasks task list
     * @param storage storage
     * @param ui ui
     * @return output for this command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        Deadline deadline = new Deadline(task, time);
        String output = tasks.add(deadline, true);
        String saveFileString = tasks.save();
        storage.save(saveFileString);
        return output;
    }
}
