package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * This class represents the command when the user types "todo" validly.
 */
public class ToDoCommand extends Command {
    private String task;

    /**
     * Constructor for ToDoCommand which takes in the task details.
     *
     * @param task task details.
     */
    public ToDoCommand(String task) {
        this.task = task;
    }

    /**
     * Adds a ToDo to the task list and saves the file.
     *
     * @param tasks task list
     * @param storage storage
     * @param ui ui
     * @return output for this command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        ToDo item = new ToDo(this.task);
        String output = tasks.add(item, true);
        String saveFileString = tasks.save();
        storage.save(saveFileString);
        return output;
    }
}
