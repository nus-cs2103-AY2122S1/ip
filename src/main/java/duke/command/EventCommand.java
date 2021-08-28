package duke.command;

import java.time.DateTimeException;
import java.time.LocalDate;

import duke.DukeList;
import duke.UserInterface;
import duke.exception.InvalidArgumentException;
import duke.task.EventTask;
import duke.task.Task;

/**
 * Represents a command to add an event task into main DukeList.
 */
public class EventCommand implements Command {
    private final DukeList dukeList;
    private final UserInterface ui;

    /**
     * Creates a command that creates an event in the given dukelist and provide its
     * response to the given ui.
     *
     * @param dukeList list of tasks for the task to be added into
     * @param ui       user interface for displaying responses
     */
    public EventCommand(DukeList dukeList, UserInterface ui) {
        this.dukeList = dukeList;
        this.ui = ui;
    }

    @Override
    public void exec(String args) {
        if (args == null || args.isEmpty()) {
            throw new InvalidArgumentException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        String[] arr = args.split(" /at ", 2);
        LocalDate date = null;
        if (arr.length >= 2) {
            try {
                date = LocalDate.parse(arr[1]);
            } catch (DateTimeException e) {
                throw new InvalidArgumentException("Invalid date format: yyyy-mm-dd");
            }
        }
        Task task = new EventTask(arr[0], date);
        this.dukeList.addTask(task);
        this.ui.showMessage("Got it. I've added this task:\n" + task + "\n" + this.dukeList.currentSizeMessage());
    }

    @Override
    public String getLabel() {
        return "event";
    }

}
