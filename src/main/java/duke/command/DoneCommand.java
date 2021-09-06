package duke.command;

import duke.DukeList;
import duke.UserInterface;
import duke.exception.InvalidArgumentException;
import duke.task.Task;

/**
 * Represents a command for which marks a task in the main dukelist as
 * completed.
 */
public class DoneCommand implements Command {
    private final DukeList dukeList;
    private final UserInterface ui;

    /**
     * Creates a command that marks a task from the given dukelist as completed, and
     * provide its response to the given ui.
     *
     * @param dukeList list of tasks from which the task will marked as completed
     * @param ui       user interface for displaying responses
     */
    public DoneCommand(DukeList dukeList, UserInterface ui) {
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
        Task task = this.dukeList.markCompleted(i);
        assert task != null;
        this.ui.showMessage("Nice! I've marked this task as done:\n" + task);
    }

    @Override
    public String getLabel() {
        return "done";
    }

}
