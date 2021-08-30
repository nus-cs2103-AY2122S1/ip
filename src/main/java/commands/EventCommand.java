package commands;

import storage.Storage;
import tasks.EventTask;
import tasks.TaskList;
import ui.Ui;

import java.util.ArrayList;

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
     * @return the message displaying the result
     */
    @Override
    public String execute(TaskList lst, Ui ui, Storage storage) {
        try {
            EventTask e = new EventTask(lst.filterInfo(getInput()),
                    lst.searchForEventDay(getInput()));
            String result = lst.addTask(e);
            storage.resetFile(lst.getTasks());
            return result;
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("event")) {
                return "Invalid input :(" + "\n" + "Please input in the form: 'event <Name> /at <Date>'.";
            } else {
                return e.getMessage() + "\n" + "I can't add an event without a date!";
            }
        }
    }
}
