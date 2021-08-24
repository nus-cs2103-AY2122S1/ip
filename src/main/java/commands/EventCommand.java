package commands;

import java.util.ArrayList;
import tasks.EventTask;
import tasks.TaskList;
import storage.Storage;
import ui.Ui;

/**
 * The EventCommand Class inherits Command and is
 * a specific type of executable command.
 */
public final class EventCommand extends Command{

    /**
     * Constructs the EventCommand object.
     *
     * @param s the entire line of user input
     */
    public EventCommand(ArrayList<String> s) {
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
            EventTask e = new EventTask(lst.filterInfo(getInput()),
                    lst.searchForEventDay(getInput()));
            lst.addTask(e);
            storage.resetFile(lst.getTasks());
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("event")) {
                Ui.showInput("Invalid input :(", "Please input in the form: 'event <Name> /at <Date>'.");
            } else {
                Ui.showInput(e.getMessage(), "I can't add an event without a date!");
            }
        }
    }
}
