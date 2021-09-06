package duke.command;

import duke.DukeList;
import duke.UserInterface;
import duke.exception.InvalidArgumentException;
import duke.task.DurationTask;
import duke.task.Task;

/**
 * Represents a command to add a fixed duration task into main DukeList.
 */
public class DurationCommand implements Command {
    private final DukeList dukeList;
    private final UserInterface ui;

    /**
     * Creates a command that creates a fixed duration task in the given dukelist
     * and provide its response to the given ui.
     *
     * @param dukeList list of tasks for the task to be added into
     * @param ui       user interface for displaying responses
     */
    public DurationCommand(DukeList dukeList, UserInterface ui) {
        this.dukeList = dukeList;
        this.ui = ui;
    }

    @Override
    public void exec(String args) {
        if (args == null || args.isEmpty()) {
            throw new InvalidArgumentException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        String[] arr = args.split(" /dura ", 2);
        int duration = 0;
        if (arr.length >= 2) {
            try {
                duration = Integer.parseInt(arr[1]);
            } catch (NumberFormatException e) {
                throw new InvalidArgumentException("Invalid duration format: integer in minutes");
            }
        }
        Task task = new DurationTask(arr[0], duration);
        this.dukeList.addTask(task);
        this.ui.showMessage("Got it. I've added this task:\n" + task + "\n" + this.dukeList.currentSizeMessage());
    }

    @Override
    public String getLabel() {
        return "duration";
    }

}
