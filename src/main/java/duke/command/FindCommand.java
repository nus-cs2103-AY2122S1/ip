package duke.command;

import duke.Ui;
import duke.Storage;
import duke.notes.NotesList;
import duke.tasks.TaskList;
import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Finds a task in the task manager based on a keyword provided by the user.
 */
public class FindCommand extends Command {
    private String command;

    /**
     * Instantiates an object of the FindCommand class.
     *
     * @param command User's input.
     */
    public FindCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the action of finding a task in the task manager application, Peppa based on a keyword.
     *
     * @param tasks List of tasks stored in the task manager.
     * @param notes List of notes stored in the task manager.
     * @param ui User interface of the task manager.
     * @param storage Hard disk containing all the tasks and notes of the task manager.
     * @return Message to be printed on the user interface to notify the user of the outcome of the input entered.
     */
    @Override
    public String execute(TaskList tasks, NotesList notes, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
            if(tasks.getTask(i).getDescription().contains(command)) {
                foundTasks.add(tasks.getTask(i));
            }
        }
        return ui.respondToFind(foundTasks);
    }

    /**
     * Returns a boolean value indicating if user wants to exit the task manager.
     *
     * @return Boolean value.
     */
    @Override
    public Boolean isExit() {
        return false;
    }
}
