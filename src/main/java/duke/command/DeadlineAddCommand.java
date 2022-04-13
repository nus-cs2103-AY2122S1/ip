package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command that adds a deadline task to the current list of tasks.
 */
public class DeadlineAddCommand extends AddCommand {

    private String description;
    private String time;

    /**
     * Stores a description and time of the task to be dealt with later.
     *
     * @param description Description of the task.
     * @param time The time of the task.
     */
    public DeadlineAddCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    /**
     * This method adds the deadline to the task list, stores the deadline into the storage and outputs
     * the current number of tasks the user has in his task list.
     *
     * @param taskList Tasklist that contains an Arraylist of agendas on the list.
     * @param ui Ui that outputs something based on the command given.
     * @param storage Storage that changes the list stored in data/duke.txt based on the command.
     * @return The message that states that Deadline has been added.
     * @throws DukeException catches an error when input is wrong.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String toPrint;
        int totalTasks = taskList.addToList("D", description, time);
        storage.addToText("D", description, time);
        toPrint = ui.addingTask(totalTasks, description, time, "D");
        return toPrint;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeadlineAddCommand)) {
            return false;
        }
        DeadlineAddCommand other = (DeadlineAddCommand) obj;
        return this.description.equals(other.description) && this.time.equals(other.time);
    }
}
