package duke.command;

import duke.Duke;
import duke.task.TaskList;
import duke.GUI;


/**
 * Represents the general list command.
 */
public class ListCommand extends Command {

    /**
     * Constructor for the List command.
     * @param duke Duke chatbot that is in use.
     */
    public ListCommand(Duke duke) {
        super(duke);
    }

    @Override
    public String execute(TaskList taskList) {
        return GUI.printReply(taskList.toString());
    }
}
