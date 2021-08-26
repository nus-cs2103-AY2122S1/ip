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
     * @throws DukeException catches an error when input is wrong.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int totalTasks = taskList.addToList("D", description, time);
        storage.addToText("D", description, time);
        ui.addingTask(totalTasks, description, time, "D");
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
