package fan.cs2103t.duke.command;

import fan.cs2103t.duke.task.TaskList;
import fan.cs2103t.duke.ui.Ui;

/**
 * Represents a Duke's command which lists all the tasks currently inside Duke's task list upon execution.
 * <p>
 * This is a subclass of the <code>Command</code> class.
 */
public class ListCommand extends Command {

    /**
     * Executes this command. Displays a message to the user through the specified UI listing all the tasks
     * currently inside the specified task list. Returns the list as a string.
     *
     * @param taskList the ask list which contains all the tasks to be listed.
     * @param ui the UI for the message to be displayed through.
     * @return the list in string format.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        return taskList.printList();
    }

}
