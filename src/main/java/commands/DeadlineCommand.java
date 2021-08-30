package commands;

import storage.Storage;
import tasks.DeadLineTask;
import tasks.TaskList;
import ui.Ui;

import java.util.ArrayList;

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
     * @return the message displaying the result
     */
    @Override
    public String execute(TaskList lst, Ui ui, Storage storage) {
        try {
            DeadLineTask d = new DeadLineTask(lst.filterInfo(getInput()),
                    lst.lookForDeadline(getInput()));
            String result = lst.addTask(d);
            storage.resetFile(lst.getTasks());
            return result;
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("deadline")) {
                return "Invalid input :(\n" +
                        "Please input in the form: 'deadline <Name> /by <Date>'.";
            } else {
               return e.getMessage() + "\n" + "Hey, no deadline recorded does not mean no deadline >:(";
            }
        }
    }
}
