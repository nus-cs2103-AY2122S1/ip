package duke;

import duke.data.TaskFileStorage;
import duke.fulfillment.FulfillmentHandler;
import duke.io.ConsoleUserInputHandler;
import duke.io.ConsoleUserOutputHandler;
import duke.io.UserOutputHandler;
import duke.tasks.TaskList;

import java.io.IOException;

/**
 * Main class representing the Duke helper chatbot.
 *
 * @author kevin9foong
 */
public class Duke {
    private final FulfillmentHandler fulfillmentHandler;

    /**
     * Instantiates an instance of the Duke chat bot which is the main functionality
     * used to fulfill user's commands.
     *
     * @throws IOException thrown when failure to read and write to input and outputs occur or
     * when failures to retrieve persisted data occur.
     */
    public Duke() throws IOException {
        ConsoleUserInputHandler consoleUserInputHandler = new ConsoleUserInputHandler();
        UserOutputHandler userOutputHandler = new ConsoleUserOutputHandler();
        TaskList taskList = new TaskList(new TaskFileStorage());
        this.fulfillmentHandler = new FulfillmentHandler(consoleUserInputHandler,
                userOutputHandler, taskList);
    }

    /**
     * Main method to run the application.
     *
     * @param args unused parameter.
     * @throws IOException thrown when errors writing or reading occur.
     */
    public static void main(String[] args) throws IOException {
        new Duke().fulfillmentHandler.runChatbot();
    }
}
