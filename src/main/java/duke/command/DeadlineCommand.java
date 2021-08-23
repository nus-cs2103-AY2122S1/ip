package duke.command;

import duke.DukeList;
import duke.UserInterface;
import duke.exception.InvalidArgumentException;
import duke.task.DeadlineTask;
import duke.task.Task;

/**
 * A command to add a deadline task into main DukeList.
 */
public class DeadlineCommand implements Command {
    private DukeList dukeList;
    private UserInterface ui;

    public DeadlineCommand(DukeList dukeList, UserInterface ui) {
        this.dukeList = dukeList;
        this.ui = ui;
    }

    @Override
    public void exec(String args) {
        if (args == null || args.isEmpty())
            throw new InvalidArgumentException("☹ OOPS!!! The description of a deadline cannot be empty.");
        Task task = new DeadlineTask(args);
        this.dukeList.addTask(task);
        this.ui.showMessage("Got it. I've added this task:\n" + task + "\n" + this.dukeList.currentSizeMessage());
    }

    @Override
    public String getLabel() {
        return "deadline";
    }

}
