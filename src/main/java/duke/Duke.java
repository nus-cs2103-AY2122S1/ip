package duke;

import duke.data.TaskFileStorage;
import duke.exceptions.DukeException;
import duke.fulfillment.FulfillmentHandler;
import duke.io.ConsoleUserInputHandler;
import duke.io.ConsoleUserOutputHandler;
import duke.io.UserInputHandler;
import duke.io.UserOutputHandler;
import duke.messages.Message;
import duke.tasks.TaskList;

/**
 * Main class representing the Duke helper chatbot.
 *
 * @author kevin9foong
 */
public class Duke {
    private FulfillmentHandler fulfillmentHandler;

    /**
     * Instantiates an instance of the Duke chat bot which is the main functionality
     * used to fulfill user's commands.
     */
    public Duke() {
        UserInputHandler userInputHandler = new ConsoleUserInputHandler();
        UserOutputHandler userOutputHandler = new ConsoleUserOutputHandler();
        try {
            TaskList taskList = new TaskList(new TaskFileStorage());
            this.fulfillmentHandler = new FulfillmentHandler(userInputHandler,
                    userOutputHandler, taskList);
        } catch (DukeException de) {
            userOutputHandler.writeMessage(new Message(de.getMessage()));
        }
    }

    /**
     * Main method to run the application.
     *
     * @param args unused parameter.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        if (duke.fulfillmentHandler != null) {
            duke.fulfillmentHandler.runChatBot();
        }
    }
}
