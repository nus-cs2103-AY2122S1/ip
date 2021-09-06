package duke.fulfillment;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.messages.GreetingMessage;
import duke.tasks.TaskList;

/**
 * This class handles user input and handles user's commands.
 *
 * @author kevin9foong
 */
public class FulfillmentHandler {
    private final TaskList taskList;

    /**
     * Constructs an instance of <code>FulfillmentHandler</code> which is in charge of
     * fulfilling user's commands by running the Duke chat bot.
     *
     * @param taskList list of tasks currently persisted.
     */
    public FulfillmentHandler(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Reads the text input by user into the GUI text field then executes command
     * and displays response message to fulfill user's command.
     *
     * @throws DukeException thrown due to invalid user input or issues loading task save file.
     */
    public String getResponseToUserInput(String userInput) throws DukeException {
        Command userCommand = Parser.parse(userInput);
        return userCommand.execute(taskList);
    }

    public String getGreetingMessage() {
        return new GreetingMessage().toString();
    }
}
