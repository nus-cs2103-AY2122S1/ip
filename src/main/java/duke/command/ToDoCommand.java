package duke.command;

import duke.DukeList;
import duke.UserInterface;
import duke.exception.InvalidArgumentException;
import duke.task.Task;
import duke.task.ToDoTask;

/**
 * Represents a command to add a todo task into main DukeList.
 */
public class ToDoCommand implements Command {
    private final DukeList dukeList;
    private final UserInterface ui;

    /**
     * Creates a todo task to be added into given dukelist and provide its response
     * onto the given ui.
     *
     * @param dukeList list with which the task will be added into
     * @param ui       for displaying responses
     */
    public ToDoCommand(DukeList dukeList, UserInterface ui) {
        this.dukeList = dukeList;
        this.ui = ui;
    }

    @Override
    public void exec(String args) {
        if (args == null || args.isEmpty()) {
            throw new InvalidArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task task = new ToDoTask(args);
        this.dukeList.addTask(task);
        this.ui.showMessage("Got it. I've added this task:\n" + task + "\n" + this.dukeList.currentSizeMessage());
    }

    @Override
    public String getLabel() {
        return "todo";
    }

}
