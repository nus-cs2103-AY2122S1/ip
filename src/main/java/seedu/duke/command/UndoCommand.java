package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents an undo command. An <code>UndoCommand</code> undoes
 * the latest command done by the user.
 */
public class UndoCommand extends Command {
    private static final String UNDO_MESSAGE = "Undid previous command.";
    private Storage storage;
    private HashMap<LocalDate, ArrayList<Task>> dateTasks;

    /**
     * Public constructor for an <code>UndoCommand</code>.
     *
     * @param ui The Ui to handle user interactions.
     * @param taskList The task list to be updated.
     * @param storage The storage that handles file modifications.
     */
    public UndoCommand(Ui ui, TaskList taskList, HashMap<LocalDate, ArrayList<Task>> dateTasks,
                       Storage storage) {
        super(ui, taskList);
        this.storage = storage;
        this.dateTasks = dateTasks;
    }

    /**
     * Returns the format on how to use the command.
     *
     * @return String representation of the help message.
     */
    @Override
    public String getUsageMessage() {
        return "undo | undo previous command";
    }

    /**
     * Check if the given command alters the task list.
     *
     * @return true if it updates the task list.
     */
    @Override
    public boolean isUpdatesTaskList() {
        return true;
    }

    /**
     * Undo the last command.
     *
     * @return Undo message.
     */
    @Override
    public String execute() throws DukeException {
        taskList = taskList.getPrevTaskList();
        storage.undo(taskList);
        Task prevTask = taskList.getPrevTask();
        /*
        Not being updated correctly
        
        LocalDate date = prevTask.getDate();
        if (!date.equals(LocalDate.now())) {
            dateTasks.get(date).remove(prevTask);
        }
         */
        return UNDO_MESSAGE;
    }

}

