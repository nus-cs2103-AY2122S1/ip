package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command that adds Event to the task list.
 */
public class EventAddCommand extends AddCommand {

    private String description;
    private String time;

    /**
     * Adding a description and a time to the Command to be used in the future.
     *
     * @param description The description of the task.
     * @param time The time of the task.
     */
    public EventAddCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    /**
     * Executes a command to add keyed in event to the task list as well as memory. Ui will output
     * a message stating the total number of tasks in the list.
     *
     * @param taskList Tasklist that contains an Arraylist of agendas on the list.
     * @param ui Ui that outputs something based on the command given.
     * @param storage Storage that changes the list stored in data/duke.txt based on the command.
     * @return A message that says that an event has been successfully added.
     * @throws DukeException catches errors in user input.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int totalTasks = taskList.addToList("E", description, time);
        storage.addToText("E", description, time);
        return ui.addingTask(totalTasks, description, time, "E");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EventAddCommand)) {
            return false;
        }
        EventAddCommand other = (EventAddCommand) obj;
        return this.description.equals(other.description) && this.time.equals(other.time);
    }
}
