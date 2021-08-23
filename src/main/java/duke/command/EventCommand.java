package duke.command;

import duke.DukeList;
import duke.UserInterface;
import duke.exception.InvalidArgumentException;
import duke.task.EventTask;
import duke.task.Task;

/**
 * A command to add an event task into main DukeList.
 */
public class EventCommand implements Command {
    private final DukeList dukeList;
    private final UserInterface ui;

    public EventCommand(DukeList dukeList, UserInterface ui) {
        this.dukeList = dukeList;
        this.ui = ui;
    }

    @Override
    public void exec(String args) {
        if (args == null || args.isEmpty())
            throw new InvalidArgumentException("☹ OOPS!!! The description of an event cannot be empty.");
        Task task = new EventTask(args);
        this.dukeList.addTask(task);
        this.ui.showMessage("Got it. I've added this task:\n" + task + "\n" + this.dukeList.currentSizeMessage());
    }

    @Override
    public String getLabel() {
        return "event";
    }

}
