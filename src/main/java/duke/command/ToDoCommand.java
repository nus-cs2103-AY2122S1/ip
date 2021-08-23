package main.java.duke.command;

import main.java.duke.DukeException;
import main.java.duke.storage.*;
import main.java.duke.tasklist.*;
import main.java.duke.Ui;
import main.java.duke.task.ToDo;

import java.io.IOException;

/**
 * ToDoCommand is a command which adds a ToDo task to the task list.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S2
 */
public class ToDoCommand extends Command {

    /**
     * Constructor.
     *
     * @param description it should contain the ToDo description and the date
     */
    public ToDoCommand(String description) {
        super(description);
    }

    /**
     * Adds the ToDo into the task list.
     *
     * @param tasks   the task list
     * @param ui      the ui
     * @param storage the storage for the saved task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (description.replace(" ", "").equals("")) {
                throw new DukeException("The description for the Todo task cannot be empty.");
            }
            String desc = description.trim();
            tasks.addTask(new ToDo(false, desc));
            storage.add("T", desc, "");
        } catch (IOException e) {
            throw new DukeException("There is an error in adding the Todo task to your saved data.");
        }
    }
}
