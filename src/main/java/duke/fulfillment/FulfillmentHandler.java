package duke.fulfillment;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.io.UserInputHandler;
import duke.io.UserOutputHandler;
import duke.messages.GreetingMessage;
import duke.messages.Message;
import duke.tasks.TaskList;

import java.io.IOException;

/**
 * This class handles user input and handles user's commands.
 *
 * @author kevin9foong
 */
public class FulfillmentHandler {
    private final UserInputHandler consoleUserInputHandler;
    private final UserOutputHandler consoleUserOutputHandler;
    private final TaskList taskList;

    /**
     * Constructs an instance of <code>FulfillmentHandler</code> which is in charge of
     * fulfilling user's commands by running the Duke chat bot.
     *
     * @param userInputHandler handles input from the user.
     * @param userOutputHandler handles displaying output to the user.
     * @param taskList list of tasks currently persisted.
     */
    public FulfillmentHandler(UserInputHandler userInputHandler,
                              UserOutputHandler userOutputHandler, TaskList taskList) {
        this.consoleUserInputHandler = userInputHandler;
        this.consoleUserOutputHandler = userOutputHandler;
        this.taskList = taskList;
    }

    /**
     * Initializes the Duke chat bot.
     *
     * @throws IOException thrown when an error connecting
     *                     to input/output stream occurs.
     */
    public void runChatbot() throws IOException {
        handleGreeting();
        boolean isExit = false;

        while (!isExit) {
            String userInput = consoleUserInputHandler.readInput();
            try {
                Command userCommand = Parser.parse(userInput);
                userCommand.execute(consoleUserOutputHandler, taskList);
                isExit = userCommand.isExit();
            } catch (DukeException e) {
                consoleUserOutputHandler.writeMessage(new Message(e.getMessage()));
            }
        }
    }

    private void handleGreeting() {
        consoleUserOutputHandler.writeMessage(new GreetingMessage());
    }
}
