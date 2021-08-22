package duke.fulfillment;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.io.ConsoleUserInputHandler;
import duke.io.ConsoleUserOutputHandler;
import duke.io.UserInputHandler;
import duke.io.UserOutputHandler;
import duke.messages.GreetingMessage;
import duke.messages.Message;
import duke.tasks.TaskList;

import java.io.IOException;

/**
 * Handles commands from user.
 *
 * @author kevin9foong
 */
public class FulfillmentHandler {
    private final UserInputHandler consoleUserInputHandler;
    private final UserOutputHandler consoleUserOutputHandler;
    private final TaskList taskList;

    public FulfillmentHandler(UserInputHandler consoleUserInputHandler,
                              UserOutputHandler consoleUserOutputHandler, TaskList taskList) {
        this.consoleUserInputHandler = consoleUserInputHandler;
        this.consoleUserOutputHandler = consoleUserOutputHandler;
        this.taskList = taskList;
    }

    /**
     * Initializes the Chatbot
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
