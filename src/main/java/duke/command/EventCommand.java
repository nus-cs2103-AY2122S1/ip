package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This class represents a {@code EventCommand}. User input for a
 * {@code EventCommand} starts with "event".
 *
 * @author Elizabeth Chow
 */
public class EventCommand extends Command {
    /**
     * Constructor for a {@code EventCommand}
     *
     * @param title    Title of the new task.
     * @param deadline Deadline of the new task.
     */
    public EventCommand(String title, String deadline) {
        super(title, deadline);
    }

    /**
     * Adds a {@code Event} to tasks and writes to storage. Displays the new task in the terminal.
     *
     * @param tasks   the tasklist to be modified.
     * @param ui      responsible for printing to the terminal.
     * @param storage stores all the tasks.
     *
     * @return String message to be displayed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert args.length == 2 : "Event Command should only store 2 arguments";
        tasks.addEventTask(args[0], args[1]);
        storage.writeToFile(tasks);
        return ui.showNewTask(tasks.lastTask(), tasks.size());
    }
}
