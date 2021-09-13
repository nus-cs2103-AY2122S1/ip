package duke.command;

import duke.Duke;
import duke.GUI;
import duke.task.TaskList;

/**
 * Represents a general bye command.
 */
public class ByeCommand extends Command{

    /**
     * Constructor for the Bye command.
     * @param duke chatbot that is in use.
     */
    public ByeCommand(Duke duke) {
        super(duke);
    }

    @Override
    public String execute(TaskList taskList) {
        this.duke.closeDukeChatBot();

        return GUI.sendClosingMessage();
    }

}
