package commands;

import java.util.ArrayList;
import storage.Storage;
import tasks.DeadLineTask;
import tasks.TaskList;
import ui.Ui;

/**
 * The DeadlineCommand Class inherits Command and is
 * a specific type of executable command.
 */
public final class DeadlineCommand extends Command{

    /**
     * Constructs the DeadlineCommand object.
     *
     * @param s the entire line of user input
     */
    public DeadlineCommand(ArrayList<String> s) {
        super(s);
    }

    /**
     * Executes the command.
     *
     * @param lst the TaskList object that stores the list of tasks
     * @param ui the Ui object that interacts with the user
     * @param storage the Storage object that saves changes to stored tasks, if any
     */
    @Override
    public void execute(TaskList lst, Ui ui, Storage storage) {
        try {
            DeadLineTask d = new DeadLineTask(lst.filterInfo(getInput()),
                    lst.lookForDeadline(getInput()));
            lst.addTask(d);
            storage.resetFile(lst.getTasks());
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("deadline")) {
                Ui.showInput("Invalid input :(",
                        "Please input in the form: 'deadline <Name> /by <Date>'.");
            } else {
                Ui.showInput(e.getMessage(), "Hey, no deadline recorded does not mean no deadline >:(");
            }
        }
    }
}
