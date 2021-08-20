package cs2103t.duke.command;

import cs2103t.duke.task.TaskList;
import cs2103t.duke.ui.Ui;

/**
 * Represents a Duke's command which lists all the tasks currently inside Duke's task list upon execution.
 * <p>
 * This is a subclass of the <code>Command</code> class.
 */
public class ListCommand extends Command {

    /**
     * Executes this command. Displays a message to the user through the specified UI listing all the tasks
     * currently inside Duke's task list.
     *
     * @param taskList the specified task list which contains all the tasks to be listed.
     * @param ui the specified UI for the message to be displayed through.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.displayText(taskList.printList());
    }

}
