package duke.command;

import java.time.DateTimeException;
import java.time.LocalDate;

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
        String[] arr = args.split(" /by ", 2);
        LocalDate date = null;
        if (arr.length >= 2) {
            try {
                date = LocalDate.parse(arr[1]);
            } catch (DateTimeException e) {
                throw new InvalidArgumentException("Invalid date format: yyyy-mm-dd");
            }
        }
        Task task = new DeadlineTask(arr[0], date);
        this.dukeList.addTask(task);
        this.ui.showMessage("Got it. I've added this task:\n" + task + "\n" + this.dukeList.currentSizeMessage());
    }

    @Override
    public String getLabel() {
        return "deadline";
    }

}
