package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command that adds a To do item to the list.
 */
public class TodoAddCommand extends AddCommand {

    private String description;

    public TodoAddCommand(String description) {
        this.description = description;
    }

    /**
     * Executes a command that adds a to do item to TaskList and Storage, and prints a message from the Ui
     * that updates how many tasks are currently in your list of tasks.
     *
     * @param taskList Tasklist that contains an Arraylist of agendas on the list.
     * @param ui Ui that outputs something based on the command given.
     * @param storage Storage that changes the list stored in data/duke.txt based on the command.
     * @return A message that tells the user that they have added a Todo.
     * @throws DukeException throws an exception for user input error.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String toPrint;
        int totalTasks = taskList.addToList("T", description, "NA");
        storage.addToText("T", description, "NA");
        toPrint = ui.addingTask(totalTasks, description, "NA", "T");
        return toPrint;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TodoAddCommand)) {
            return false;
        }
        TodoAddCommand other = (TodoAddCommand) obj;
        return this.description.equals(other.description);
    }
}
