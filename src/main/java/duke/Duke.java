package duke;

import duke.data.TaskFileStorage;
import duke.fulfillment.FulfillmentHandler;
import duke.io.ConsoleUserInputHandler;
import duke.io.ConsoleUserOutputHandler;
import duke.io.UserOutputHandler;
import duke.tasks.TaskList;

import java.io.IOException;

public class Duke {
    private final FulfillmentHandler fulfillmentHandler;

    public Duke() throws IOException {
        ConsoleUserInputHandler consoleUserInputHandler = new ConsoleUserInputHandler();
        UserOutputHandler userOutputHandler = new ConsoleUserOutputHandler();
        TaskList taskList = new TaskList(new TaskFileStorage());
        this.fulfillmentHandler = new FulfillmentHandler(consoleUserInputHandler,
                userOutputHandler, taskList);
    }

    public static void main(String[] args) throws IOException {
        new Duke().fulfillmentHandler.runChatbot();
    }
}
