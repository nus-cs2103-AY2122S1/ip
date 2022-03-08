package duke.ui;

import duke.TaskList;
import duke.storage.TaskStorage;

/**
 * Represents a Graphical User Interface (GUI) that the user interacts with.
 */
public class Gui extends Ui {
    /**
     * Constructs a GUI with the user's storage and tasks.
     *
     * @param taskStorage The user's storage of tasks in the hard disk.
     * @param taskList The user's list of tasks.
     */
    public Gui(TaskStorage taskStorage, TaskList taskList) {
        super(taskStorage, taskList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
    }

}
