package duke.fulfillment;

import java.io.IOException;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.io.UserInputHandler;
import duke.io.UserOutputHandler;
import duke.messages.GreetingMessage;
import duke.messages.Message;
import duke.tasks.TaskList;

/**
 * This class handles user input and handles user's commands.
 *
 * @author kevin9foong
 */
public class FulfillmentHandler {
    private final UserInputHandler userInputHandler;
    private final UserOutputHandler userOutputHandler;
    private final TaskList taskList;

    /**
     * Constructs an instance of <code>FulfillmentHandler</code> which is in charge of
     * fulfilling user's commands by running the Duke chat bot.
     *
     * @param userInputHandler  handles input from the user.
     * @param userOutputHandler handles displaying output to the user.
     * @param taskList          list of tasks currently persisted.
     */
    public FulfillmentHandler(UserInputHandler userInputHandler,
                              UserOutputHandler userOutputHandler, TaskList taskList) {
        this.userInputHandler = userInputHandler;
        this.userOutputHandler = userOutputHandler;
        this.taskList = taskList;
    }

    /**
     * Initializes the CLI version of the Duke chat bot.
     */
    public void runCliChatBot() {
        handleGreeting();
        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = userInputHandler.readInput();
                Command userCommand = Parser.parse(userInput);
                userCommand.execute(userOutputHandler, taskList);
                isExit = userCommand.isExit();
            } catch (DukeException e) {
                userOutputHandler.writeMessage(new Message(e.getMessage()));
            } catch (IOException ioe) {
                userOutputHandler.writeMessage(new Message("OOPS!!! Unable to connect to "
                        + "user input stream!"));
            }
        }
    }

    /**
     * Displays setup actions for GUI version of the chat bot which currently
     * only includes displaying a greeting message.
     */
    public void runGuiChatBotSetup() {
        handleGreeting();
    }

    /**
     * Reads the text input by user into the GUI text field then executes command
     * and displays response message to fulfill user's command.
     */
    public void handleGuiUserCommandInput() {
        try {
            String userCommandInput = userInputHandler.readInput();
            Command userCommand = Parser.parse(userCommandInput);
            userCommand.execute(userOutputHandler, taskList);
            if (userCommand.isExit()) {
                System.exit(0);
            }
        } catch (DukeException e) {
            userOutputHandler.writeMessage(new Message(e.getMessage()));
        } catch (IOException ioe) {
            userOutputHandler.writeMessage(new Message("OOPS!!! Unable to connect to "
                    + "user input stream!"));
        }
    }

    private void handleGreeting() {
        userOutputHandler.writeMessage(new GreetingMessage());
    }
}
