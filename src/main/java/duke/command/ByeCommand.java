package duke.command;

import duke.Duke;
import duke.task.TaskList;
import duke.Ui;

import java.util.Scanner;

/**
 * Represents a general bye command.
 */
public class ByeCommand extends Command{
    /**
     * Constructor for the bye command.
     * @param duke chatbot that is in use.
     * @param sc scanner that is in use.
     */
    public ByeCommand(Duke duke, Scanner sc) {
        super(duke, sc);
    }

    @Override
    public void execute(TaskList taskList) {
        Ui.printClosingMessage();
        this.duke.closeDukeChatBot();
        this.sc.close();
    }

}
