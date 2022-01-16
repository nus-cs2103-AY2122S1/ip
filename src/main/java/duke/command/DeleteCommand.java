package duke.command;

import duke.DukeList;
import duke.UserInterface;
import duke.exception.InvalidArgumentException;
import duke.task.Task;

/**
 * Represents a command that removes a task from the main DukeList.
 */
public class DeleteCommand implements Command {
    private final DukeList dukeList;
    private final UserInterface ui;

    /**
     * Creates a command that deletes a task from the given dukelist, and provide
     * its response to the given ui.
     *
     * @param dukeList list of tasks from which the task will be deleted from
     * @param ui       user interface for displaying responses
     */
    public DeleteCommand(DukeList dukeList, UserInterface ui) {
        this.dukeList = dukeList;
        this.ui = ui;
    }

    @Override
    public void exec(String args) {
        int i = 0;
        try {
            i = Integer.parseInt(args);
        } catch (NumberFormatException exception) {
            throw new InvalidArgumentException("Invalid Input! Please enter an integer...");
        }
        Task task = this.dukeList.deleteTask(i);
        assert task != null;
        this.ui.showMessage("Noted. I've removed this task:\n" + task + "\n" + this.dukeList.currentSizeMessage());
    }

    @Override
    public String getLabel() {
        return "delete";
    }

}
